package com.bupt.uta.Service;

import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CartVo;

import java.util.List;


public interface CartService {

    List<CartVo> getCartVoList(Cart cart);
}
