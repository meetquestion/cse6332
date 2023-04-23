package com.bupt.uta.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CartVo;

import java.util.List;


public interface CartService extends IService<Cart> {

    List<CartVo> getCartVoList(Cart cart);
}
