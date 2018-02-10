package service;

import dao.ProductDao;
import entity.product.Brand;
import entity.product.Category;
import entity.product.Product;

import java.util.ArrayList;
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


    public List<Product> searchProducts(Category category, String title, List<Brand> brands, int limit, int offset) {
        return ProductDao.newInstance().searchProducts(category, title, brands, limit, offset);
    }
}
