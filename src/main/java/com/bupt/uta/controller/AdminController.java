package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.AdminService;
import com.bupt.uta.common.R;

import com.bupt.uta.entity.Admin;
import com.bupt.uta.utils.SendEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.GeneralSecurityException;

@Slf4j
@RestController
@RequestMapping("/api")
public class AdminController {
    @Resource
    private AdminService adminService = null;

    @PostMapping("/register/admin")
    public R<Admin> save(HttpServletRequest request, Admin admin){
        // System.out.println(customer.toString());
        log.info("注冊信息： {}", admin.toString());


        if(admin.getUsername().equals("")){
            return R.error("Please input valid user name.");
        }
        if(admin.getPassword().equals("")){
            return R.error("Please input valid password.");
        }

        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername, admin.getUsername());
        Admin admin1 = adminService.getOne(lambdaQueryWrapper);
        if(admin1 != null){
            return R.error("The username has been registered");
        }
        String psd = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        log.info("加密后的密码是: {}", psd);
        admin.setPassword(psd);
        adminService.save(admin);
        return R.success(admin);
    }
    @PostMapping("/login/admin")
    public R<Admin> login(HttpServletRequest request, Admin admin){
        log.info("登录信息：{}", admin);
        if(admin.getUsername().equals("")){
            return R.error("Please input valid username");
        }
        if(admin.getPassword().equals("")){
            return R.error("Please input valid password.");
        }
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername, admin.getUsername());
        Admin admin1 = adminService.getOne(lambdaQueryWrapper);
        if(admin1 == null){
            return R.error("Login failed. The username is not registered yet.");
        }
        String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        if(!admin1.getPassword().equals(password)){
            return R.error("Login failed. The password is wrong.");
        }
        request.getSession().setAttribute("customer", admin1.getUsername());
        return R.success(admin1);
    }
    @GetMapping("/logout/admin")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return R.success("Successfully");
    }
}
