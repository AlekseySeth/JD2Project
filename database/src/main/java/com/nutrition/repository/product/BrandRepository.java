package com.nutrition.repository.product;

import com.nutrition.entity.product.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, Long> {

    List<Brand> findAll();
}
