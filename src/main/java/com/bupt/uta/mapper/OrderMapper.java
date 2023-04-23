package com.bupt.uta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.CustomerOrder;
import com.bupt.uta.entity.CustomerOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface OrderMapper extends BaseMapper<CustomerOrder> {

    @Select("select * from customer_order")
    List<CustomerOrder> getOrderList();

    @Select("select status, count(1) as count from customer_order group by status")
    List<CustomerOrderVo> getCount();
}
