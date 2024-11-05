package com.indeed.poc.products.repository;

import com.indeed.poc.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByPriceBetween(int minPrice, int maxPrice);
}
