package service;

import dao.ProductDao;
import entity.product.Product;

import java.util.List;

/**
 * @author a.shestovsky
 */
public class ProductService {

    private static ProductService instance;

    public static ProductService newInstance() {
        if (instance == null) {
            synchronized (ProductService.class) {
                if (instance == null) {
                    instance = new ProductService();
                }
            }
        }
        return instance;
    }


    public List<Product> searchProducts(Long categoryId, String title, List<Long> brandsId, int limit, int offset) {
        return ProductDao.newInstance().searchProducts(categoryId, title, brandsId, limit, offset);
    }
}
