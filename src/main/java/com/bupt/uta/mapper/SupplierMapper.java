package com.bupt.uta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SupplierMapper extends BaseMapper<Product> {
}
