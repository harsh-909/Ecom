package com.ecom.Ecom.commonUtils;

import com.ecom.Ecom.dtos.FakeStoreProductDto;
import com.ecom.Ecom.models.Category;
import com.ecom.Ecom.models.Product;

public class ProductServiceManager {

    public Product convertDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();

        if(fakeStoreProductDto.getId() != null) product.setId(fakeStoreProductDto.getId());
        if(fakeStoreProductDto.getTitle() != null) product.setTitle(fakeStoreProductDto.getTitle());
        if(fakeStoreProductDto.getDescription() != null) product.setDescription(fakeStoreProductDto.getDescription());
        if(fakeStoreProductDto.getCategory() != null) product.setCategory(new Category());
        if(fakeStoreProductDto.getCategory() != null) product.getCategory().setCatName(fakeStoreProductDto.getCategory());
        if(fakeStoreProductDto.getImage() != null) product.setImageURL(fakeStoreProductDto.getImage());
        return product;
    }

    public Product updateProduct(Product newProduct, Product oldProduct){
        if(newProduct.getTitle() != null) oldProduct.setTitle(newProduct.getTitle());
        if(newProduct.getDescription() != null) oldProduct.setDescription(newProduct.getDescription());
        if(newProduct.getPrice() != null) oldProduct.setPrice(newProduct.getPrice());
        if(newProduct.getImageURL() != null) oldProduct.setImageURL(newProduct.getImageURL());
        if(newProduct.getCategory() !=null){
            if(newProduct.getCategory().getCatName() != null) oldProduct.getCategory().setCatName(newProduct.getCategory().getCatName());
        }
        return oldProduct;
    }
}
