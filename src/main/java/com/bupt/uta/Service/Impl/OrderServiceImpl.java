package com.bupt.uta.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.uta.Service.OrderService;
import com.bupt.uta.entity.CustomerOrder;
import com.bupt.uta.entity.CustomerOrderVo;
import com.bupt.uta.entity.OrderDetail;
import com.bupt.uta.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, CustomerOrder> implements OrderService {
    @Autowired
    private  OrderMapper orderMapper;
    @Override
    public List<CustomerOrder> getOrderList() {
        return orderMapper.getOrderList();
    }

    @Override
    public List<CustomerOrderVo> getCount() {
        List<CustomerOrderVo> count = orderMapper.getCount();
        return  count;
    }

    @Override
    public List<OrderDetail> getOrderDetailList(CustomerOrder customerOrder) {

        try{
            List<OrderDetail> orderDetailList = orderMapper.getOrderDetailList(customerOrder);
            return orderDetailList;
        }catch(Exception e){
            log.error(e.getMessage());
            return null;
        }

    }


    /*@Transactional
    @Override
    public Map<Integer, Integer> getOrderCount() {

        Map<Integer, Integer> map = new HashMap<>();
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.groupBy("status");
        wrapper.select("status, count(1) as count");
        List<Order> orderList = orderMapper.selectList(wrapper);
        for(Order order : orderList){
            //map.put(order.getStatus(),order.getCount());
        }
        return map;
    }*/


}
