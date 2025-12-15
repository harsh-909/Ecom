package com.ecom.Ecom.controllers;


import com.ecom.Ecom.dtos.FakeStoreProductDto;
import com.ecom.Ecom.exception.FakeStoreProductNotFoundException;
import com.ecom.Ecom.exceptionDtos.ExceptionDto;
import com.ecom.Ecom.models.Product;
import com.ecom.Ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Product getSingleProduct(@PathVariable("id") Long id) throws FakeStoreProductNotFoundException {
        return productService.getSingleProduct(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new ResponseEntity<Product>(
                productService.partialUpdateProduct(id,product), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new ResponseEntity<Product>(
                productService.replaceProduct(id,product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAProduct(@PathVariable("id") Long id){
        productService.deleteAProduct(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

//    @ExceptionHandler(FakeStoreProductNotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleProductNotFoundException(FakeStoreProductNotFoundException exception){
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage(exception.getMessage());
//        return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
//    }

}
