package com.nutrition.product;

import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;

import java.util.List;
import java.util.spi.LocaleNameProvider;

public interface ProductService {

    void save(Product product);

    Product findById(Long id);

//    void update(Product product);

    Product findByTitle(String title);

    List<Product> findByTitleCategoryBrands(String title, Category category, List<Brand> brands);

    List<Product> findByTitleCategoryBrandsViaId(String title, Long categoryId, List<Long> brandIds,
                                                 int pageNumber, int productsOnPage);
}
