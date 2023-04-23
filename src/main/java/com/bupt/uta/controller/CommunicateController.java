package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.CommunicateService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Communicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class CommunicateController {

    @Autowired
    private CommunicateService communicateService;

    @RequestMapping(value = "/communicate/get", method = RequestMethod.GET)
    public R<Communicate> getCommunicate(@RequestBody Communicate communicate){
        if(communicate == null){
            return R.error("error");
        }
        try{
            LambdaQueryWrapper<Communicate> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Communicate::getAdminId,communicate.getAdminId())
                    .eq(Communicate::getCustomerId, communicate.getCustomerId());
            Communicate one = communicateService.getOne(wrapper);
            return R.success(one);
        }catch (Exception e){
            log.error(e.getMessage());
            return R.error("error");
        }

    }
}
