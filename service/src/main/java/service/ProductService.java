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

    public List<Product> findByCategory(Category category) {
        return  ProductDao.newInstance().findByCategory(category);
    }

    public List<Product> findByBrands(Brand... brands) {
        List<Product> result = new ArrayList<>();
        ProductDao productDao = ProductDao.newInstance();
        for (Brand brand : brands) {
            result.addAll(productDao.findByBrand(brand));
        }
        return result;
    }

    public List<Product> findByTitle(String title) {
        return ProductDao.newInstance().findByTitle(title);
    }
}
