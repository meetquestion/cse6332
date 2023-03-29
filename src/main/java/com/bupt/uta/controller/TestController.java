package com.bupt.uta.controller;

import com.bupt.uta.common.R;
import com.bupt.uta.entity.Admin;
import com.bupt.uta.entity.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class TestController {
    @PostMapping("/register/customer")
    public R<Customer> saveCustomer(HttpServletRequest request, Customer customer){
        System.out.println(customer.toString());
        if(customer.getUsername().equals(""))
            return R.error("注册失败");
        else
            return R.success(customer);
    }
    @PostMapping("/register/admin")
    public R<Admin> saveAdmin(HttpServletRequest request, Admin admin){
        System.out.println(admin.toString());
        if(admin.getUsername().equals(""))
            return R.error("注册失败");
        else
            return R.success(admin);
    }
    @PostMapping("/login/customer")
    public R<Customer> login(HttpServletRequest request,
                             Customer customer){
        if(customer.getEmail().equals(""))
            return R.error("登录失败");
        return R.success(customer);
    }
    @PostMapping("/login/admin")
    public R<Admin> login(HttpServletRequest request,
                             Admin admin){
        if(admin.getUsername().equals(""))
            return R.error("登录失败");
        return R.success(admin);
    }
}
