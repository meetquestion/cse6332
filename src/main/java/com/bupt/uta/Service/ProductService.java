package com.bupt.uta.Service;

import com.bupt.uta.common.R;
import com.bupt.uta.entity.Product;

import java.util.List;

public interface ProductService{
    List<Product> getAllProduct();
    Product getProduct(long id);
    List<Product> getSuppProduct(long supplierid);
    String addProduct(Product product);
    String deleteProduct(long id);
    String updateProduct(Product product);
}
