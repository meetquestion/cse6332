package com.bupt.uta.Service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.bupt.uta.entity.CustomerOrder;
import com.bupt.uta.entity.CustomerOrderVo;
import com.bupt.uta.entity.OrderDetail;

import java.util.List;


public interface OrderService extends IService<CustomerOrder> {

    //Map<Integer,Integer> getOrderCount();
    List<CustomerOrder> getOrderList();

    List<CustomerOrderVo> getCount();

    List<OrderDetail> getOrderDetailList( Long customerId);
}
