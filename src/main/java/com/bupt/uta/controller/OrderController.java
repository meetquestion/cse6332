package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.CartService;
import com.bupt.uta.Service.OrderService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CustomerOrder;
import com.bupt.uta.entity.CustomerOrderVo;
import com.bupt.uta.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            Date date = formatter.parse(dateString);
            for(CustomerOrder order : orders){
                order.setStatus(0);
                order.setCreateTime(date);
                order.setModifyTime(date);
            }
            orderService.saveBatch(orders);
            for(CustomerOrder order : orders){
                LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Cart::getCustomerId, order.getCustomerId())
                        .eq(Cart::getProductId,order.getProductId());
                cartService.remove(wrapper);
            }
        }catch (Exception e){
            log.error(e.getMessage());
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
    // select all 测试通过的，可以传column:value,也可以不传
    /*@RequestMapping(value = "/order/get",method = RequestMethod.GET)
    public R<List<CustomerOrder>> getOrderList(@RequestBody Map<String,Object> map) {
        try{
            List<CustomerOrder> list = orderService.listByMap(map);
            return R.success(list);
        }catch(Exception e){
            log.error(e.getMessage());
            return R.error("error");
        }
    }*/

    //count 0:buy  1:return
    @RequestMapping(value = "/order/count",method = RequestMethod.GET)
    public R<List<CustomerOrderVo>> getOrderCount() {
        List<CustomerOrderVo> count = orderService.getCount();
        Map<Integer, Integer> map = new HashMap<>();
        if(count.size()!=0){
            for(CustomerOrderVo vo : count){
                map.put(vo.getStatus(),1);
            }
            if(map.get(1)==null){
                CustomerOrderVo customerOrderVo = new CustomerOrderVo();
                customerOrderVo.setCount(0);
                customerOrderVo.setStatus(1);
                count.add(customerOrderVo);
            }
            if(map.get(0)==null){
                CustomerOrderVo customerOrderVo = new CustomerOrderVo();
                customerOrderVo.setCount(0);
                customerOrderVo.setStatus(0);
                count.add(customerOrderVo);
            }
        }else{
            for(int i = 0;i<2;i++){
                CustomerOrderVo customerOrderVo = new CustomerOrderVo();
                customerOrderVo.setCount(0);
                customerOrderVo.setStatus(i);
                count.add(customerOrderVo);
            }
        }
        if(count==null){
            return R.error("error,null");
        }else {
            return R.success(count);
        }
    }

    @RequestMapping(value = "/order/getOrder",method = RequestMethod.GET)
    public R<List<CustomerOrder>> getCustomerOrderList(@RequestBody CustomerOrder customerOrder) {
        try{

            if(customerOrder.getCustomerId()!=null){
                LambdaQueryWrapper<CustomerOrder> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(CustomerOrder :: getCustomerId,customerOrder.getCustomerId());
                List<CustomerOrder> orderList = orderService.list(wrapper);
                return R.success(orderList);
            }else {
                List<CustomerOrder> orderList = orderService.getOrderList();
                return R.success(orderList);
            }

        }catch(Exception e){
            return R.error("error");
        }
    }

    @RequestMapping(value = "/order/get",method = RequestMethod.GET)
    public R<List<OrderDetail>> getOrderDetailList(CustomerOrder customerOrder) {
        try{

            List<OrderDetail> orderDetailList = orderService.getOrderDetailList(customerOrder);
            return R.success(orderDetailList);


        }catch(Exception e){
            log.error(e.getMessage());
            return R.error("error");
        }
    }
}
