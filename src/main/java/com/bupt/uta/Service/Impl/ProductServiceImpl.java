package com.bupt.uta.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.uta.Service.ProductService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CustomerOrder;
import com.bupt.uta.entity.Product;
import com.bupt.uta.mapper.CartMapper;
import com.bupt.uta.mapper.OrderMapper;
import com.bupt.uta.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Product> getAllProduct(){
        List<Product> productsList = productMapper.selectList(null);
        return productsList;
    }

    @Override
    public List<Product> getCategoryProduct(String category){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("category",category);
        List<Product> productList=productMapper.selectList(wrapper);
        return productList;
    }

    @Override
    public Product getProduct(long id) {
        Product product=productMapper.selectById(id);
        return product;
    }

    @Override
    public List<Product> getSuppProduct(long supplierid) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("supplierid",supplierid);
        List<Product> productList=productMapper.selectList(wrapper);
        return productList;
    }

    @Override
    public String addProduct(Product product) {
        try {
            productMapper.insert(product);
        }catch (Exception e){
            return "Some error happen";
        }
        return "Insert successfully";
    }

    @Override
    public String deleteProduct(long id) {
        try {
            productMapper.deleteById(id);
            LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Cart::getProductId,id);
            cartMapper.delete(wrapper);
            LambdaQueryWrapper<CustomerOrder> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(CustomerOrder::getProductId,id);
            orderMapper.delete(wrapper1);
        }catch (Exception e){
            return "Some error happen";
        }
        return "Delete successfully";
    }

    @Override
    public String updateProduct(Product product) {
        try {
            productMapper.updateById(product);
        }catch (Exception e){
            return "Some error happen";
        }
        return "Delete successfully";
    }

}
