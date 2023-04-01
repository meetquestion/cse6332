package com.bupt.uta.Service;

import com.bupt.uta.common.R;
import com.bupt.uta.entity.Product;

import java.util.List;

public interface ProductService{
    R<List<Product>> getAllProduct();
    R<Product> getProduct(int id);
    R<List<Product>> getSuppProduct(int supplierid);
    R<String> addProduct(Product product);
    R<String> deleteProduct(int id);
    R<String> updateProduct(Product product);
}
