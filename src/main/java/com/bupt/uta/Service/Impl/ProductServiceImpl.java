package com.bupt.uta.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.uta.Service.ProductService;
import com.bupt.uta.entity.Product;
import com.bupt.uta.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllProduct(){
        List<Product> productsList = productMapper.selectList(null);
        return productsList;
    }

    @Override
    public Product getProduct(int id) {
        Product product=productMapper.selectById(id);
        return product;
    }


}
