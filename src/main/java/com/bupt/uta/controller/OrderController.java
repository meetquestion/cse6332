package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.CartService;
import com.bupt.uta.Service.OrderService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.CustomerOrder;
import com.bupt.uta.entity.CustomerOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/order/get1/{id}",method = RequestMethod.GET)
    public R<String> getString(@PathVariable Long id) {
        return R.success(id+"");
        /*try{
            List<Order> list = orderService.getOrderList();
            return R.success(list);
        }catch(Exception e){
            return R.error("error");
        }*/
    }

    //add to order
    @RequestMapping(value = "/order/post",method = RequestMethod.POST)
    public R<List<CustomerOrder>> addOrder(@RequestBody List<CustomerOrder> orders) {
        if(orders == null){
            return R.error("error,null");
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = new Date().toString();
            Date date = simpleDateFormat.parse(dateStr);
            for(CustomerOrder order : orders){
                order.setStatus(0);
                order.setCreateTime(date);
                order.setModifyTime(date);
            }
            orderService.saveBatch(orders);
        }catch (Exception e){
            return R.error("error");
        }
        return R.success(orders);
    }
    //delete to order
    @RequestMapping(value = "/order/delete/{id}",method = RequestMethod.DELETE)
    public R<Long> deleteToOrder(@PathVariable Long id) {
        if(id == null){
            return R.error("error,id is null");
        }
        try {
            LambdaQueryWrapper<CustomerOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(CustomerOrder::getId, id);
            CustomerOrder order = orderService.getOne(lambdaQueryWrapper);
            if(order!=null){
                //删除
                orderService.removeById(id);
            }else{
                return R.error("Order is not exist!");
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return R.error("error");
        }
        return R.success(id);
    }

    //update to order
    @RequestMapping(value = "/order/update",method = RequestMethod.PUT)
    public R<CustomerOrder> updateToOrder(@RequestBody CustomerOrder order) {
        if(order == null){
            return R.error("error,order is null");
        }
        try {
            LambdaQueryWrapper<CustomerOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(CustomerOrder::getId, order.getId());
            CustomerOrder order1 = orderService.getOne(lambdaQueryWrapper);
            if(order1!=null){
                //update
                if(order.getCustomerId()!=null){
                    order1.setCustomerId(order.getCustomerId());
                }
                if(order.getProductId()!=null){
                    order1.setProductId(order.getProductId());
                }
                String s = order.getStatus() + "";
                if(!"".equals(s)){
                    order1.setStatus(order.getStatus());
                }
                if(order.getModifyTime()!=null){
                    order1.setModifyTime(order.getModifyTime());
                }
                orderService.updateById(order1);
                return R.success(order1);
            }else{
                return R.error("Order is not exist!");
            }
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return R.error("error");
        }
    }
    // select all
    @RequestMapping(value = "/order/get",method = RequestMethod.GET)
    public R<List<CustomerOrder>> getOrderList() {
        try{
            List<CustomerOrder> list = orderService.list();
            return R.success(list);
        }catch(Exception e){
            log.error(e.getMessage());
            return R.error("error");
        }
    }

    //count 0:buy  1:return
    @RequestMapping(value = "/order/count",method = RequestMethod.GET)
    public R<List<CustomerOrderVo>> getOrderCount() {
        List<CustomerOrderVo> count = orderService.getCount();
        if(count==null){
            return R.error("error,null");
        }else {
            return R.success(count);
        }
    }

    @RequestMapping(value = "/order/getOrder",method = RequestMethod.GET)
    public R<List<CustomerOrder>> getCartVoList() {
        try{
            List<CustomerOrder> orderList = orderService.getOrderList();
            return R.success(orderList);
        }catch(Exception e){
            return R.error("error");
        }
    }
}
