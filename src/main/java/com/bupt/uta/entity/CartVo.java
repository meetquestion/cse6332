package com.bupt.uta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVo {
    private int cartId;
    private Long customerId;
    private int productId;
    private int status;
    private String productName;
    private String coding;
    private double price;
    private String pic;
    private int num;
}
