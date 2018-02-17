package com.nutrition.repository.product;

import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findByTitleCategoryBrands(String title, Category category, List<Brand> brands);

    List<Product> findByTitleCategoryBrandsViaId(String title, Long categoryId, List<Long> brandIds);
}
