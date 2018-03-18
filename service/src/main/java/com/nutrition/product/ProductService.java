package com.nutrition.product;

import com.nutrition.dto.ProductDto;
import com.nutrition.entity.product.Product;
import com.nutrition.util.ProductSearchFilter;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void save(ProductDto productDto);

    void save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findAllByCategory(Long categoryId);

    List<Product> findProductsByFilter(ProductSearchFilter productSearchFilter, Integer pageNumber, Integer qtyOnPage);

    Integer countPagesByFilter(ProductSearchFilter productSearchFilter, Integer qtyOnPage);

    void update(Product product, String title, String description, BigDecimal price,
                int qtyInStock, String imageURL, Long categoryId, Long brandId);
}