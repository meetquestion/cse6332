package com.bupt.uta.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(locale="zh", timezone="GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
