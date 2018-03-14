package com.nutrition.product;

import com.nutrition.entity.product.Product;
import com.nutrition.util.ProductSearchFilter;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findAllByCategory(Long categoryId);

    List<Product> findProductsByFilter(ProductSearchFilter productSearchFilter, int pageNumber, int qtyOnPage);

    int countPagesByFilter(ProductSearchFilter productSearchFilter, int qtyOnPage);

    void update(Product product, String title, String description, BigDecimal price,
                int qtyInStock, String imageURL, Long categoryId, Long brandId);
}
