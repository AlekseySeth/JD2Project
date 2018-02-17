package com.nutrition.repository.product;

import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, ProductRepositoryCustom {

    Product findByTitle(String title);

    List<Product> findAllByCategory(Category category);
}
