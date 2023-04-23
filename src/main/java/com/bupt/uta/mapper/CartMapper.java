package com.bupt.uta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CartVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select cart.id as cartId, customer_id as customerId, product_id as productId, status, pro.name as productName, coding, price,category, pic,num " +
            "from cart left join product pro on product_id = pro.id where customer_id = #{customerId} and status = 1")
    List<CartVo> getCartList(Cart cart);

}
