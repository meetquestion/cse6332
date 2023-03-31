package com.bupt.uta.controller;

import com.bupt.uta.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/home")
    public R<String> getHome(){
        return R.success("Login");
    }
}
