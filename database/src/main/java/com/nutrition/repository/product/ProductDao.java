package com.nutrition.repository.product;

import com.nutrition.repository.common.GenericDao;
import com.nutrition.entity.product.Product;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface ProductDao extends GenericDao<Product> {

    Product findByTitle(String title);

    List<Product> findByCategoryTitleBrands(Long categoryId, String title, List<Long> brandsId, int limit, int offset);
}
