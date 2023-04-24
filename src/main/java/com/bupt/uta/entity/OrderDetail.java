package com.bupt.uta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    //orderId
    private Long id;
    private String customerName;
    private String productName;
    private String category;
    private String pic;
    private int status;
    private double price;
    private Date CreateTime;

}
