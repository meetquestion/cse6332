package com.bupt.uta.controller;

import com.bupt.uta.Service.CartService;
import com.bupt.uta.common.R;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author jingxp
 * @version 1.0
 * @date Created in 2023年03月31日 16:16
 * @since 1.0
 */
@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/getCartVoList",method = RequestMethod.GET)
    public R<List<CartVo>> getCartVoList(Long customerId) {
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
