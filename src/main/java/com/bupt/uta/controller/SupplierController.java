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

    @RequestMapping(value = "/suppProducts/{supplierid}",method = RequestMethod.GET)
    public String getSuppProducts(@PathVariable int supplierid){
        Map<String,Object> map=new HashMap<>();
        try{
            List<Product> productList=supplierService.getSuppProduct(supplierid);
            if (productList!=null&&productList.size()>0){
                map.put("status","200");
                map.put("data",productList);
            }else{
                map.put("status","-1");
                map.put("message","no product");
            }
        }catch (Exception ex){
            map.put("status","500");//执行出现异常
            map.put("message","error:"+ex.getMessage());
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public String addProduct(HttpServletRequest request,Product product){
        try {
            System.out.println("add");
            int i = supplierService.addProduct(product);
            if(i<0){
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping(value = "/deleteProduct/{id}",method = RequestMethod.POST)
    public String deleteProduct(@PathVariable int id){
        try {
            int i = supplierService.deleteProduct(id);
            if(i<0){
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
    public String updateProduct(HttpServletRequest request,Product product){
        try {
            int i = supplierService.updateProduct(product);
            if(i<0){
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
