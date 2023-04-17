package com.bupt.uta.controller;

import com.alibaba.fastjson.JSON;
import com.bupt.uta.Service.ProductService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value="/category",method = RequestMethod.GET)
    public R<List<Product>> getAllProducts(HttpServletRequest request){
        return R.success(productService.getAllProduct());
    }

    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    public R<Product> getProduct(@PathVariable long id){
        return R.success(productService.getProduct(id));
    }

    @RequestMapping(value = "/suppProducts/{supplierid}",method = RequestMethod.GET)
    public R<List<Product>> getSuppProducts(@PathVariable long supplierid){
        return R.success(productService.getSuppProduct(supplierid));
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public R<String> addProduct(HttpServletRequest request,Product product){
        return R.success(productService.addProduct(product));
    }

    @RequestMapping(value = "/deleteProduct/{id}",method = RequestMethod.POST)
    public R<String> deleteProduct(@PathVariable long id){
        return R.success(productService.deleteProduct(id));
    }

    @RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
    public R<String> updateProduct(HttpServletRequest request,Product product){
        return R.success(productService.updateProduct(product));
    }

}