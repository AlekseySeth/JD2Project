package dao;

import entity.product.Brand;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class BrandDao {

    private static BrandDao INSTANCE;

    public static BrandDao newInstance() {
        if (INSTANCE == null) {
            synchronized (CategoryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BrandDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Brand> getAll() {
        return null;
    }

}
