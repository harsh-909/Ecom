package com.ecom.Ecom.repositories;

import com.ecom.Ecom.models.Category;
import com.ecom.Ecom.models.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @NonNull
    Optional<Product> findById(@NonNull Long id);

    @NonNull
    List<Product> findAll();

    @NonNull
    Product save(@NonNull Product product);

    void deleteById(@NonNull Long Id);

    Product findByProductAndCategory(Product product, Category category);

    List<Product> findByCategory(Category category);



}
