package com.bupt.uta.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bupt.uta.Service.CartService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.PriorityQueue;

@Slf4j
@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/getCartVoList/{customerId}",method = RequestMethod.GET)
    public R<List<CartVo>> getCartVoList(@PathVariable Long customerId) {
        if(customerId==null){
            return R.error("error");
        }
        try{

            PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2)->o2.compareTo(o1));

            Cart cart = new Cart();
            cart.setCustomerId(customerId);
            List<CartVo> cartVoList = cartService.getCartVoList(cart);
            return R.success(cartVoList);
        }catch(Exception e){
            return R.error("error");
        }
    }
    //add to cart
    @RequestMapping(value = "/cart/save",method = RequestMethod.POST)
    public R<Cart> addToCart(@RequestBody Cart cart){
        if(cart == null){
            return R.error("error,cart is null");
        }
        try{
            //如果原来有，修改操作，数量相加
            LambdaQueryWrapper<Cart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Cart::getCustomerId, cart.getCustomerId())
                    .eq(Cart::getProductId, cart.getProductId())
                    .eq(Cart::getStatus, cart.getStatus());
            Cart cart1 = cartService.getOne(lambdaQueryWrapper);
            if(cart1 !=null){
                int num = cart1.getNum();
                cart1.setNum(cart.getNum()+num);
                cartService.updateById(cart1);
                return R.success(cart1);
            }else{
                //没有，新增
                cartService.save(cart);
                return R.success(cart);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return R.error("error");
        }
    }

    //delete to cart
    @RequestMapping(value = "/cart/put/{id}",method = RequestMethod.PUT)
    public R<Long> deleteToCart(@PathVariable Long id) {
        if(id == null){
            return R.error("error,id is null");
        }
        try {
            LambdaQueryWrapper<Cart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Cart::getId, id);
            Cart cart = cartService.getOne(lambdaQueryWrapper);
            if(cart!=null){
                //删除
                cart.setStatus(0);
                cartService.updateById(cart);
            }else{
                return R.error("Cart is not exist!");
            }
        }catch(Exception e){
            return R.error("error");
        }
        return R.success(id);
    }
}
