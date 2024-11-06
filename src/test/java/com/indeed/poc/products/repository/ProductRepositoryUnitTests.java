package com.indeed.poc.products.repository;

import com.indeed.poc.products.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@Slf4j
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepositoryUnitTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Test 1:Save Product Test")
    @Order(1)
    @Rollback(value = false)
    public void saveProductTest(){
        //Action
        Product product = Product.builder()
                .barcode("74000001")
                .item("Shirt")
                .category("Full Body Outfits")
                .price(1)
                .discount(0)
                .available(1)
                .build();

        productRepository.save(product);

        //Verify
        log.info(product.toString());
        Assertions.assertThat(product.getBarcode()).isEqualTo("74000001");
    }

    @Test
    @Order(2)
    public void getListOfProductTest(){
        //Action
        List<Product> products = productRepository.findAll();
        //Verify
        log.info(products.toString());
        Assertions.assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void findByPriceBetweenTest(){

        //Action
        List<Product> products = productRepository.findByPriceBetween(1,10);
        //Verify
        log.info(products.toString());
        Assertions.assertThat(products.size()).isGreaterThan(0);
    }
}
