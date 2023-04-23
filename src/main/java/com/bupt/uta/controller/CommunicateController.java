package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.AdminService;
import com.bupt.uta.Service.CommunicateService;
import com.bupt.uta.Service.CustomerService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Admin;
import com.bupt.uta.entity.Communicate;
import com.bupt.uta.entity.CommunicateVo;
import com.bupt.uta.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @RequestMapping(value = "/communicate/get", method = RequestMethod.GET)
    public R<Communicate> getCommunicate(@RequestBody Communicate communicate){
        if(communicate == null){
            return R.error("error");
        }
        try{
            LambdaQueryWrapper<Communicate> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Communicate::getReceiverId,communicate.getReceiverId())
                    .eq(Communicate::getSenderId, communicate.getSenderId());
            Communicate one = communicateService.getOne(wrapper);
            return R.success(one);
        }catch (Exception e){
            log.error(e.getMessage());
            return R.error("error");
        }

    }

    @RequestMapping(value = "/communicate/post",method = RequestMethod.POST)
    public R<CommunicateVo> save(@RequestBody Communicate communicate){
        if(communicate == null){
            return R.error("error");
        }
        CommunicateVo communicateVo = new CommunicateVo();

        try{
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            Date date = formatter.parse(dateString);
            communicate.setCreateTime(date);
            communicateService.save(communicate);

            LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Admin:: getId,communicate.getReceiverId());
            Admin admin = adminService.getOne(wrapper);
            if(admin!=null){
                communicateVo.setAdminName(admin.getUsername());
            }

            LambdaQueryWrapper<Customer> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(Customer:: getId,communicate.getReceiverId());
            Customer customer = customerService.getOne(wrapper1);
            if(customer!=null){
                communicateVo.setCustomerName(customer.getUsername());
            }
            communicateVo.setContent(communicate.getContent());
            communicateVo.setCreateTime(communicate.getCreateTime());
            communicateVo.setReceiverId(communicate.getReceiverId());
            communicateVo.setSenderId(communicate.getSenderId());

        }catch (Exception e){
            log.error(e.getMessage());
            R.error("error,e");
        }
        return R.success(communicateVo);
    }
}
