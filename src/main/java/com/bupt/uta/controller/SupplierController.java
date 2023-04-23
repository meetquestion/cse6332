package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.SupplierService;
import com.bupt.uta.common.R;

import com.bupt.uta.entity.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api")
public class SupplierController {
    @Resource
    private SupplierService supplierService = null;
    @PostMapping("/register/supplier")
    public R<Supplier> save(HttpServletRequest request, Supplier admin) {
        // System.out.println(customer.toString());
        log.info("注冊信息： {}", admin.toString());


        if (admin.getUsername().equals("")) {
            return R.error("Please input valid user name.");
        }
        if (admin.getPassword().equals("")) {
            return R.error("Please input valid password.");
        }

        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Supplier::getUsername, admin.getUsername());
        Supplier admin1 = supplierService.getOne(lambdaQueryWrapper);
        if (admin1 != null) {
            return R.error("The username has been registered");
        }
        String psd = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        log.info("加密后的密码是: {}", psd);
        admin.setPassword(psd);
        supplierService.save(admin);
        return R.success(admin);
    }

    @PostMapping("/login/supplier")
    public R<Supplier> login(HttpServletRequest request, Supplier admin) {
        log.info("登录信息：{}", admin);
        if (admin.getUsername().equals("")) {
            return R.error("Please input valid username");
        }
        if (admin.getPassword().equals("")) {
            return R.error("Please input valid password.");
        }
        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Supplier::getUsername, admin.getUsername());
        Supplier admin1 = supplierService.getOne(lambdaQueryWrapper);
        if (admin1 == null) {
            return R.error("Login failed. The username is not registered yet.");
        }
        String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        if (!admin1.getPassword().equals(password)) {
            return R.error("Login failed. The password is wrong.");
        }
        request.getSession().setAttribute("user", admin1.getUsername()+"-"+admin1.getId());
        log.info("supplier:{}", request.getSession().getAttribute("user"));
        return R.success(admin1);
    }

    @GetMapping("/logout/supplier")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return R.success("Successfully");
    }
}
