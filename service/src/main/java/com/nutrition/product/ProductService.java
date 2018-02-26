package com.nutrition.product;

import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    void save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    Page<Product> findAll(int pageNumber, int qtyOnPage);

//    void update(Product product);

    Product findByTitle(String title);

    List<Product> findAllByCategory(Category category);

    List<Product> findByTitleCategoryBrands(String title, Category category, List<Brand> brands);

    List<Product> findByTitleCategoryBrandsViaId(String title, Long categoryId, List<Long> brandIds,
                                                 int pageNumber, int productsOnPage);
}
