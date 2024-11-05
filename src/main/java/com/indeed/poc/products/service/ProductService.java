package com.indeed.poc.products.service;

import com.indeed.poc.products.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByPrice(int initialRange, int finalRange);

    List<Product> getSortedProductsByPrice();
}
