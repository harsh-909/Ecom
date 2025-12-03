package com.ecom.Ecom.controllers;


import com.ecom.Ecom.dtos.FakeStoreProductDto;
import com.ecom.Ecom.exception.fakeStoreProductNotFoundException;
import com.ecom.Ecom.models.Product;
import com.ecom.Ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products/")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public Product addSingleProduct(@RequestBody Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setCategory(product.getCategory().getCatName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setImage(product.getImageURL());
        fakeStoreProductDto.setPrice(product.getPrice().toString());
        return productService.addSingleProduct(fakeStoreProductDto);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws fakeStoreProductNotFoundException {
        return productService.getSingleProduct(id);
    }

    @PatchMapping
    public Product partialUpdateProduct(@RequestBody Product product){
        product.setDescription("Partially product updated");
        return product;
    }

    @PutMapping
    public Product fullUpdateProduct(@RequestBody Product product){
        product.setDescription("Fully product updated");
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteAProduct(@PathVariable("id") Long id){

    }

}
