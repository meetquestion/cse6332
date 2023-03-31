package com.bupt.uta.Service.Impl;

import com.bupt.uta.Service.CartService;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CartVo;
import com.bupt.uta.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<CartVo> getCartVoList(Cart cart) {
        List<CartVo> cartVoList = cartMapper.getCartList(cart);
        return cartVoList;
    }
}
