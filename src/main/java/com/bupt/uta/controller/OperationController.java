package com.bupt.uta.controller;

import com.bupt.uta.Service.OperationService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Operation;
import com.bupt.uta.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @RequestMapping(value="/allOperation",method = RequestMethod.GET)
    public R<List<Operation>> getAllProducts(HttpServletRequest request){
        return R.success(operationService.getAllOperation());
    }

    @RequestMapping(value="/addOperation/{operation}",method = RequestMethod.POST)
    public R<String> addOperation(@PathVariable String operation){
        return R.success(operationService.addOperation(operation));
    }
}
