package com.ecom.Ecom.services;

import com.ecom.Ecom.dtos.FakeStoreProductDto;
import com.ecom.Ecom.exception.FakeStoreProductNotFoundException;
import com.ecom.Ecom.models.Product;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {


    public @NonNull Optional<Product> getSingleProduct(Long id) throws FakeStoreProductNotFoundException;

    public List<Product> getAllProducts() ;

    public Product addSingleProduct(FakeStoreProductDto productDTO);

    public Product replaceProduct(Long id, Product product);

    public Product partialUpdateProduct(Long id, Product product);

    public void deleteAProduct(Long id);
}
