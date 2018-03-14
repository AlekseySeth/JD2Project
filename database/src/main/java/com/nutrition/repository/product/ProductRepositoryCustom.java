package com.nutrition.repository.product;

import com.nutrition.entity.product.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findProductsByFilter(String title, Long categoryId, List<Long> brandIds, int productsOnPage, int offset);

    long countProductsByFilter(String title, Long categoryId, List<Long> brandIds);
}
