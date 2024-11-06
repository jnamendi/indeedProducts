package com.indeed.poc.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indeed.poc.products.entity.Product;
import com.indeed.poc.products.service.ProductService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ProductController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    Product product;

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

    //Get Controller
    @Test
    @Order(1)
    public void getProductsByPriceTest() throws Exception{
        // precondition
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(Product.builder().barcode("74000002").item("Jeans").category("Full Body Outfits").price(9).discount(0).available(1).build());
        given(productService.getProductsByPrice(1,10)).willReturn(productList);

        // action
        ResultActions response = mockMvc.perform(get("/product/filter/price/1/10"));

        // verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(productList.size())));

    }



}
