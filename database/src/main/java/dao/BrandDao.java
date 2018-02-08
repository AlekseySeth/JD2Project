package dao;

import entity.product.Brand;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class BrandDao extends GenericDao<Brand> {

    private static BrandDao instance;

    public static BrandDao newInstance() {
        if (instance == null) {
            synchronized (CategoryDao.class) {
                if (instance == null) {
                    instance = new BrandDao();
                }
            }
        }
        return instance;
    }

}
