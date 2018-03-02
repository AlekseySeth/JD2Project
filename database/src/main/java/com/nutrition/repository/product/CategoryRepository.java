package com.nutrition.repository.product;

import com.nutrition.entity.product.Category;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@CacheConfig(cacheNames = "categories")
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Cacheable
    List<Category> findAll();
}
