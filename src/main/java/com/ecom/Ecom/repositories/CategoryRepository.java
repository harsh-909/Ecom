package com.ecom.Ecom.repositories;

import com.ecom.Ecom.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
