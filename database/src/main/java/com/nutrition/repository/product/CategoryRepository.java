package com.nutrition.repository.product;

import com.nutrition.entity.product.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();
}
