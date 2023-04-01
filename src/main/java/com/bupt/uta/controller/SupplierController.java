package com.bupt.uta.controller;

import com.alibaba.fastjson.JSON;
import com.bupt.uta.Service.SupplierService;
import com.bupt.uta.entity.Product;
import com.bupt.uta.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

}
