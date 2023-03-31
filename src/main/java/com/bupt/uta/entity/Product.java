package com.bupt.uta.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String category;
    private String name;
    private String color;
    private String coding;
    private String size;
    private double price;
    private String pic;
}