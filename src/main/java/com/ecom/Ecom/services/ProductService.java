package com.ecom.Ecom.services;

import com.ecom.Ecom.dtos.FakeStoreProductDto;
import com.ecom.Ecom.exception.fakeStoreProductNotFoundException;
import com.ecom.Ecom.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {


    public Product getSingleProduct(Long id) throws fakeStoreProductNotFoundException;

    public List<Product> getAllProducts() ;

    public Product addSingleProduct(FakeStoreProductDto productDTO);

    public Product replaceProduct(Long id, Product product);

    public Product partialUpdateProduct(Long id, Product product);

    public void deleteAProduct(Long id);
}
