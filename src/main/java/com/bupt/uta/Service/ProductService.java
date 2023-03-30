package com.bupt.uta.Service;

import com.bupt.uta.entity.Product;

import java.util.List;

public interface ProductService{
    List<Product> getAllProduct();
    Product getProduct(int id);
}
