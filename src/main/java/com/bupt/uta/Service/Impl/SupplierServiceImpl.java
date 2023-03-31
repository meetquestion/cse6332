package com.bupt.uta.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bupt.uta.Service.SupplierService;
import com.bupt.uta.entity.Product;
import com.bupt.uta.mapper.SupplierMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<Product> getSuppProduct(int supplierid) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("supplierid",supplierid);
        List<Product> productList=supplierMapper.selectList(wrapper);
        return productList;
    }

    @Override
    public int addProduct(Product product) {
        int ans=supplierMapper.insert(product);
        return ans;
    }

    @Override
    public int deleteProduct(int id) {
        int ans=supplierMapper.deleteById(id);
        return ans;
    }

    @Override
    public int updateProduct(Product product) {
        int ans=supplierMapper.updateById(product);
        return ans;
    }
}
