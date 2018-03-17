package com.nutrition.repository.product;

import com.nutrition.entity.product.Product;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author a.shestovsky
 */
@CacheConfig(cacheNames = "products")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, ProductRepositoryCustom {

    List<Product> findAllByCategoryId(Long categoryId);

    List<Product> findAll();
}
