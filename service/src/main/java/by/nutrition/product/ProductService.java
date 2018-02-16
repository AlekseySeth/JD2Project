package by.nutrition.product;

import by.nutrition.entity.product.Product;

import java.util.List;

public interface ProductService {

    Long save(Product product);

    Product findById(Long id);

    void update(Product product);

    Product findByTitle(String title);

    List<Product> findByCategoryTitleBrands(Long categoryId, String title, List<Long> brandsId, int limit, int offset);
}
