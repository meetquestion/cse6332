package com.bupt.uta.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.uta.entity.CustomerOrder;
import com.bupt.uta.entity.CustomerOrderVo;
import com.bupt.uta.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface OrderMapper extends BaseMapper<CustomerOrder> {

    @Select("select * from customer_order")
    List<CustomerOrder> getOrderList();

    @Select("select status, count(1) as count from customer_order group by status")
    List<CustomerOrderVo> getCount();

    /*@Select("select cor.id, cu.username as customerName, pro.name as productName, pro.category, pro.pic, pro.price as price, cor.status, cor.create_time from customer_order cor " +
            "left join product pro on cor.product_id = pro.id " +
            "left join customer cu on cor.customer_id = cu.id " +
            "where 1=1" +
            " '<if test = 'customerId !=null' >' " +
            "and cor.customer_id = #{customerId}" +
            "'</if>'")*/
    @Select({
            "<script>",
            "select cor.id, cu.username as customerName, pro.name as productName, pro.category, pro.pic, pro.price as price, cor.status, cor.create_time from customer_order cor " +
                    "left join product pro on cor.product_id = pro.id " +
                    "left join customer cu on cor.customer_id = cu.id " +
                    "where 1=1" ,
            " <if test='customerId !=null'>and cor.customer_id =#{customerId,jdbcType=BIGINT} </if>",
            "</script>"
    })
    List<OrderDetail> getOrderDetailList(CustomerOrder customerOrder);
}
