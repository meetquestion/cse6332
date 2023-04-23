package com.bupt.uta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {
    private Long id;
    private Long customerId;
    private Long productId;
    // 0:pay 1: return
    private int status;

    private Date modifyTime;
    private Date createTime;
}
