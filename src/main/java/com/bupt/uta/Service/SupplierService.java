package com.bupt.uta.Service;

import com.bupt.uta.entity.Product;

import java.util.List;

public interface SupplierService {
    List<Product> getSuppProduct(int supplierid);
    int addProduct(Product product);
    int deleteProduct(int id);
    int updateProduct(Product product);
}
