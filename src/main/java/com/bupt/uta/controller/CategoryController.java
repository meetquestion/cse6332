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
public class CategoryController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value="/category",method = RequestMethod.GET)
    public String getAllProducts(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        try{
            List<Product> productsList =productService.getAllProduct();
            if(productsList!=null&&productsList.size()>0){
                map.put("status",200);
                map.put("data",productsList);
            }else{
                map.put("status",-1);
                map.put("message","no product");
            }
        }catch (Exception ex){
            map.put("status",500);//执行出现异常
            map.put("message","error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    public String getProduct(@PathVariable int id){
        Map<String,Object> map = new HashMap<>();
        try{
            Product product=productService.getProduct(id);
            if(product!=null){
                map.put("status",200);
                map.put("data",product);
            }else{
                map.put("status",-1);
                map.put("message","no product");
            }
        }catch (Exception ex){
            map.put("status",500);//执行出现异常
            map.put("message","error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

}
