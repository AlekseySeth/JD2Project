package by.nutrition.product;

import by.nutrition.entity.product.Category;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface CategoryService {

    Category findById(Long id);

    List<Category> findAll();
}
