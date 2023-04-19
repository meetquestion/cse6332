package com.bupt.uta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.Cart;
import com.bupt.uta.entity.CartVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
 * @date Created in 2023年03月31日 15:53
 * @since 1.0
 */
@Mapper
@Repository
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select cart.id as cartId, customer_id as customerId, product_id as productId, status, pro.name as productName, coding, price,category, pic,num " +
            "from cart left join product pro on product_id = pro.id where customer_id = #{customerId}")
    List<CartVo> getCartList(Cart cart);
}
