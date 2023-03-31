package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.CustomerService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Customer;
import com.bupt.uta.utils.SendEmail;
import com.bupt.uta.utils.Utils;
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
public class CustomerController {
    @Resource
    private CustomerService customerService = null;

    @PostMapping("/register/customer")
    public R<Customer> save(HttpServletRequest request, Customer customer){
        // System.out.println(customer.toString());
        log.info("注冊信息： {}", customer.toString());


        if(!Utils.isValidPhoneNumber(customer.getPhone())){
            return R.error("Wrong phone number.");
        }


        if(customer.getUsername().equals("")){
            return R.error("Please input valid user name.");
        }
        if(customer.getPassword().equals("")){
            return R.error("Please input valid password.");
        }
        if(customer.getEmail().equals("")){
            return R.error("Please input valid email.");
        }
        if(customer.getAddress().equals("")){
            return R.error("Please input valid address.");
        }
        if(customer.getPhone().equals("")){
            return R.error("Please input valid phone.");
        }

        LambdaQueryWrapper<Customer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Customer::getEmail, customer.getEmail());
        Customer cus = customerService.getOne(lambdaQueryWrapper);
        if(cus != null){
            return R.error("The email has been registered");
        }
        String psd = DigestUtils.md5DigestAsHex(customer.getPassword().getBytes());
        log.info("加密后的密码是: {}", psd);
        String code = psd.substring(psd.length() - 6);
        log.info("验证码是: {}", code);
        customer.setPassword(psd);
        try {
            customerService.save(customer);
            SendEmail.send(customer, code);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return R.error("Failed to send verification code. Please check your email or network");
        }
        return R.success(customer);
    }
    @PostMapping("/login/customer")
    public R<Customer> login(HttpServletRequest request, Customer customer, String verificationCode){
        log.info("登录信息：{} 验证码是：{}", customer, verificationCode);

        if(customer.getEmail().equals("")){
            return R.error("Please input valid email");
        }

        LambdaQueryWrapper<Customer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Customer::getEmail, customer.getEmail());
        Customer cus = customerService.getOne(lambdaQueryWrapper);

        if(cus == null){
            return R.error("Login failed. The email is not registered yet.");
        }

        String password = DigestUtils.md5DigestAsHex(customer.getPassword().getBytes());

        if(!cus.getPassword().equals(password)){
            return R.error("Login failed. The password is wrong.");
        }

        if(!password.substring(password.length() - 6).equals(verificationCode)){
            return R.error("VerificationCode is wrong");
        }

        request.getSession().setAttribute("customer", cus.getUsername());
        log.info("session:{} ", request.getSession().getAttribute("customer"));
        return R.success(cus);
    }
    @GetMapping("/logout/customer")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("customer");
        return R.success("Successfully");
    }

}
