package com.bupt.uta.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.uta.Service.ProductService;
import com.bupt.uta.common.R;
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
    public R<List<Product>> getAllProduct(){
        List<Product> productsList = productMapper.selectList(null);
        return R.success(productsList);
    }

    @Override
    public R<Product> getProduct(int id) {
        Product product=productMapper.selectById(id);
        return R.success(product);
    }

    @Override
    public R<List<Product>> getSuppProduct(int supplierid) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("supplierid",supplierid);
        List<Product> productList=productMapper.selectList(wrapper);
        return R.success(productList);
    }

    @Override
    public R<String> addProduct(Product product) {
        try {
            productMapper.insert(product);
        }catch (Exception e){
            return R.error("Some error happen");
        }
        return R.success("Insert successfully");
    }

    @Override
    public R<String> deleteProduct(int id) {
        try {
            productMapper.deleteById(id);
        }catch (Exception e){
            return R.error("Some error happen");
        }
        return R.success("Delete successfully");
    }

    @Override
    public R<String> updateProduct(Product product) {
        try {
            productMapper.updateById(product);
        }catch (Exception e){
            return R.error("Some error happen");
        }
        return R.success("Delete successfully");
    }

}
