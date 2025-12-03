package com.ecom.Ecom.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private String price;
    private String description;
    private String category;
    private String image;
}
