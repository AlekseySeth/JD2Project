package dao;

import entity.product.Brand;
import entity.product.Category;
import entity.product.Product;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class ProductDao extends GenericDao<Product> {

    private static ProductDao instance;

    public static ProductDao newInstance() {
        if (instance == null) {
            synchronized (ProductDao.class) {
                if (instance == null) {
                    instance = new ProductDao();
                }
            }
        }
        return instance;
    }

    public List<Product> findByCategory(Category category) {
        return null;
    }

    public List<Brand> findByBrand(Brand brand) {
        return null;
    }

}
