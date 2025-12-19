package com.ecom.Ecom.services;

import com.ecom.Ecom.commonUtils.ProductServiceManager;
import com.ecom.Ecom.dtos.FakeStoreProductDto;
import com.ecom.Ecom.models.Product;
import com.ecom.Ecom.repositories.CategoryRepository;
import com.ecom.Ecom.repositories.ProductRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductServiceManager productServiceManager;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public @NonNull Optional<Product> getSingleProduct(Long id){
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addSingleProduct(FakeStoreProductDto productDTO) {
        Product product = productServiceManager.convertDtoToProduct(productDTO);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product1 =null;
        if(optionalProduct.isPresent()){
            product1 = optionalProduct.get();
            productServiceManager.updateProduct(product, product1);
            productRepository.save(product1);
        }
        return product1;
    }

    @Override
    public Product partialUpdateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product1 =null;
        if(optionalProduct.isPresent()){
            product1 = optionalProduct.get();
            productServiceManager.updateProduct(product, product1);
            productRepository.save(product1);
        }
        return product1;
    }

    @Override
    public void deleteAProduct(Long id) {
        productRepository.deleteById(id);
    }
}
