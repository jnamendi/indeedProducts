package com.indeed.poc.products.service;

import com.indeed.poc.products.entity.Product;
import com.indeed.poc.products.repository.ProductRepository;
import com.indeed.poc.products.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceUnitTests {
    private static final String SORT_BY = "price";

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setup(){

        product = Product.builder()
                .barcode("74000001")
                .item("Shirt")
                .category("Full Body Outfits")
                .price(1)
                .discount(0)
                .available(1)
                .build();
    }

    @Test
    @Order(1)
    public void getProductsByPrice(){
        Product product1 = Product.builder()
                .barcode("74000002")
                .item("Jeans")
                .category("Full Body Outfits")
                .price(9)
                .discount(0)
                .available(1)
                .build();

        // precondition
        given(productRepository.findByPriceBetween(1,10)).willReturn(List.of(product,product1));

        // action
        List<Product> productList = productService.getProductsByPrice(1,10);

        // verify
        log.info(productList.toString());
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isGreaterThan(1);
    }

    @Test
    @Order(2)
    public void getSortedProductsByPrice() {
        Product product1 = Product.builder()
                .barcode("74000002")
                .item("Jeans")
                .category("Full Body Outfits")
                .price(9)
                .discount(0)
                .available(1)
                .build();

        // precondition
        Sort sort = Sort.by(SORT_BY).ascending();
        given(productRepository.findAll(sort)).willReturn(List.of(product,product1));
        // action
        List<Product> productList = productService.getSortedProductsByPrice();

        // verify
        log.info(productList.toString());
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isGreaterThan(1);
        assertThat(productList.get(0).getBarcode()).isEqualTo("74000001");
    }

}
