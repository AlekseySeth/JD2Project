package dao;

import entity.product.Product;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class ProductDao {

    private static ProductDao INSTANCE;

    public static ProductDao newInstance() {
        if (INSTANCE == null) {
            synchronized (ProductDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProductDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Product> getAll() {
        return null;
    }

}
