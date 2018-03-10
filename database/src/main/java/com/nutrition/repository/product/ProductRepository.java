package com.nutrition.repository.product;

import com.nutrition.entity.product.Product;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author a.shestovsky
 */
@CacheConfig(cacheNames = "products")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, ProductRepositoryCustom {

    Product findByTitleContaining(String title);

    @Cacheable
    List<Product> findAllByCategoryId(Long categoryId);

    @Cacheable
    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    void update(Product product);
}
