package com.ecom.Ecom.services;

import com.ecom.Ecom.dtos.FakeStoreProductDto;
import com.ecom.Ecom.exception.fakeStoreProductNotFoundException;
import com.ecom.Ecom.models.Category;
import com.ecom.Ecom.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class FakeStoreAPIService implements ProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public FakeStoreAPIService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    Product convertToFakeStoreProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getTitle());
        product.setCategory(new Category());
        product.getCategory().setCatName(fakeStoreProductDto.getCategory());
        product.setImageURL(fakeStoreProductDto.getImage());
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) throws fakeStoreProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        if(fakeStoreProductDto == null){
            throw new fakeStoreProductNotFoundException("Fake Store Product Not found");
        }
        return convertToFakeStoreProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] allFakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<Product> productList = new ArrayList<>();

        if(allFakeStoreProductDtos == null) return productList;

        for(FakeStoreProductDto dto : allFakeStoreProductDtos){
            productList.add(convertToFakeStoreProduct(dto));
        }
        return productList;
    }

    @Override
    public Product addSingleProduct(FakeStoreProductDto productDTO) {
        restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productDTO,
                FakeStoreProductDto.class
        );
        return convertToFakeStoreProduct(productDTO);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,restTemplate.getMessageConverters());
        FakeStoreProductDto response =  restTemplate.execute("https://fakestoreapi.com/products", HttpMethod.PUT, requestCallback, responseExtractor);
        return convertToFakeStoreProduct(response);
    }


}
