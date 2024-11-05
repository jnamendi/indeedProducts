package com.indeed.poc.products.controller;


import com.indeed.poc.products.entity.Product;
import com.indeed.poc.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = ProductController.PATH)
@Tag(name = "product", description = "Request mapping for servicing product api calls")
public class ProductController {
    public static final String PATH = "/product";
    @Autowired
    private ProductService productService;

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/product/filter/price/{initial_range}/{final_range}
     * Purpose: Get a collection of products whose price is between `initial_range` and `final_range`
     * @return List of products within the price range
     */
    @Operation(summary = "Get a collection of products whose price is between `initial_range` and `final_range`", responses = {
            @ApiResponse(responseCode = "200", description = "Returns an array of products within the price range"),
            @ApiResponse(responseCode = "400", description = "One parameter is missing or invalid in the specified range",
                    content = @Content)
    })
    @GetMapping("/filter/price/{initial_range}/{final_range}")
    public ResponseEntity<List<Product>> getAllEmployees(@PathVariable Integer initial_range,@PathVariable Integer final_range){
        return ResponseEntity.ok().body(productService.getProductsByPrice(initial_range, final_range));
    }

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/product/sort/price
     * Purpose: Get a collection of products sorted by price in ascending order
     * @return List of product names sorted from lowest to highest price
     */
    @Operation(summary = "Get a collection of products sorted by price in ascending order`", responses = {
            @ApiResponse(responseCode = "200", description = "Returns an array of product names sorted from lowest to highest price")
    })
    @GetMapping("/sort/price")
    public ResponseEntity<List<Product>> getSortedProductsByPrice(){
        return ResponseEntity.ok().body(productService.getSortedProductsByPrice());
    }
}
