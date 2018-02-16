package by.nutrition.product;

import by.nutrition.dao.product.ProductDao;
import by.nutrition.entity.product.Product;
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
    public List<Product> searchProducts(Long categoryId, String title, List<Long> brandsId, int limit, int offset) {
        return productDao.searchProducts(categoryId, title, brandsId, limit, offset);
    }
}
