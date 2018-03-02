package com.nutrition.repository.product;

import com.nutrition.entity.product.Brand;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@CacheConfig(cacheNames = "brands")
public interface BrandRepository extends CrudRepository<Brand, Long> {

    @Cacheable
    List<Brand> findAll();
}
