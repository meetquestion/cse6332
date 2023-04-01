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


}
