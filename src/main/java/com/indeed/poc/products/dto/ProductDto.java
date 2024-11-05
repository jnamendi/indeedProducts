package com.indeed.poc.products.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private String barcode;
    private String item;
    private String category;
    private Integer price;
    private Integer discount;
    private Integer available;
}
