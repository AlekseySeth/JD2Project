package com.nutrition.product;

import com.nutrition.repository.product.ProductDao;
import com.nutrition.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author a.shestovsky
 */

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Long save(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public Product findByTitle(String title) {
        return productDao.findByTitle(title);
    }

    @Override
    public List<Product> findByCategoryTitleBrands(Long categoryId, String title, List<Long> brandsId, int limit, int offset) {
        return productDao.findByCategoryTitleBrands(categoryId, title, brandsId, limit, offset);
    }
}
