package com.indeed.poc.products.service.impl;

import com.indeed.poc.products.entity.Product;
import com.indeed.poc.products.repository.ProductRepository;
import com.indeed.poc.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String SORT_BY = "price";

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductsByPrice(int initialRange, int finalRange)
    {
        return productRepository.findByPriceBetween(initialRange,finalRange);
    }


    @Override
    public List<Product> getSortedProductsByPrice()
    {
        Sort sort = Sort.by(SORT_BY).ascending();
        return productRepository.findAll(sort);
    }


}
