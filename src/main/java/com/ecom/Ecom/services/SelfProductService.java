package com.ecom.Ecom.services;

import com.ecom.Ecom.dtos.FakeStoreProductDto;
import com.ecom.Ecom.exception.FakeStoreProductNotFoundException;
import com.ecom.Ecom.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("selfProductService")
public class SelfProductService implements ProductService{


    @Override
    public Product getSingleProduct(Long id) throws FakeStoreProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product addSingleProduct(FakeStoreProductDto productDTO) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product partialUpdateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteAProduct(Long id) {

    }
}
