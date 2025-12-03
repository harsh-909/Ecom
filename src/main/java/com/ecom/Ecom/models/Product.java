package com.ecom.Ecom.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;

    private String title;

    private String description;

    private String imageURL;

    private Category category;

    private Double price;
}
