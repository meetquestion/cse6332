package com.bupt.uta.controller;

import com.bupt.uta.Service.CartService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            Cart cart = new Cart();
            cart.setCustomerId(customerId);
            List<CartVo> cartVoList = cartService.getCartVoList(cart);
            return R.success(cartVoList);
        }catch(Exception e){
            return R.error("error");
        }
    }
}
