package by.nutrition.product;

import by.nutrition.entity.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> searchProducts(Long categoryId, String title, List<Long> brandsId, int limit, int offset);
}
