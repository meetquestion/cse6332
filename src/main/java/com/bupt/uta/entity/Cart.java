package com.bupt.uta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long id;
    private Long customerId;
    private Long productId;
    // 1 有效，0：无效（已删除）
    private int status;
    private int num;

}
