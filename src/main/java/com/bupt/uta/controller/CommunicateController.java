package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.AdminService;
import com.bupt.uta.Service.CommunicateService;
import com.bupt.uta.Service.CustomerService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Communicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class CommunicateController {

    @Autowired
    private CommunicateService communicateService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/communicate/get/{senderId}/{receiverId}", method = RequestMethod.GET)
    public R<List<Communicate>> getCommunicate(@PathVariable Long senderId, @PathVariable Long receiverId){
        if(senderId == null || receiverId == null){
            return R.error("error");
        }
        try{
            List<Communicate> communicateList = communicateService.getCommunicateList(senderId, receiverId);
            return R.success(communicateList);
        }catch (Exception e){
            log.error(e.getMessage());
            return R.error("error");
        }

    }

    @RequestMapping(value = "/communicate/post",method = RequestMethod.POST)
    public R<Communicate> save(@RequestBody Communicate communicate){
        if(communicate == null){
            return R.error("error");
        }
        try{
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            Date date = formatter.parse(dateString);
            communicate.setCreateTime(date);
            communicateService.save(communicate);

        }catch (Exception e){
            log.error(e.getMessage());
            R.error("error,e");
        }
        return R.success(communicate);
    }
}
