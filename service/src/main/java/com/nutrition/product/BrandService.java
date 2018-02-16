package com.nutrition.product;

import com.nutrition.entity.product.Brand;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface BrandService {

    Brand findById(Long id);

    List<Brand> findAll();
}
