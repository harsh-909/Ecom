package com.ecom.Ecom.services;

import com.ecom.Ecom.commonUtils.ProductServiceManager;
import com.ecom.Ecom.dtos.FakeStoreProductDto;
import com.ecom.Ecom.exception.FakeStoreProductNotFoundException;
import com.ecom.Ecom.models.Category;
import com.ecom.Ecom.models.Product;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("fakeStoreProductService")
public class FakeStoreAPIService implements ProductService {

    private final RestTemplate restTemplate;
    private ProductServiceManager productServiceManager;

    @Autowired
    public FakeStoreAPIService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public @NonNull Optional<Product> getSingleProduct(Long id) throws FakeStoreProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        if(fakeStoreProductDto == null){
            throw new FakeStoreProductNotFoundException("Fake Store Product Not found");
        }
        return Optional.ofNullable(productServiceManager.convertDtoToProduct(fakeStoreProductDto));
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
            productList.add(productServiceManager.convertDtoToProduct(dto));
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
        return productServiceManager.convertDtoToProduct(productDTO);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice().toString());
        fakeStoreProductDto.setCategory(product.getCategory().getCatName());
        fakeStoreProductDto.setImage(product.getImageURL());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,restTemplate.getMessageConverters());
        FakeStoreProductDto response =  restTemplate.execute("https://fakestoreapi.com/products", HttpMethod.PUT, requestCallback, responseExtractor);
        return productServiceManager.convertDtoToProduct(response);
    }

    @Override
    public Product partialUpdateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        if(product.getId() != null) fakeStoreProductDto.setId(product.getId());
        if(product.getTitle() != null) fakeStoreProductDto.setTitle(product.getTitle());
        if(product.getDescription() != null) fakeStoreProductDto.setDescription(product.getDescription());
        if(product.getPrice() != null) fakeStoreProductDto.setPrice(product.getPrice().toString());
        if(product.getCategory() != null) fakeStoreProductDto.setCategory(product.getCategory().getCatName());
        if(product.getImageURL() != null) fakeStoreProductDto.setImage(product.getImageURL());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,restTemplate.getMessageConverters());
        FakeStoreProductDto response =  restTemplate.execute("https://fakestoreapi.com/products", HttpMethod.PUT, requestCallback, responseExtractor);
        return productServiceManager.convertDtoToProduct(response);
    }

    @Override
    public void deleteAProduct(Long id){
        restTemplate.delete("https://fakestoreapi.com/products/1'" + id);
    }


}
