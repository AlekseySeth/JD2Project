package dao;

import entity.product.Brand;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class BrandDao {

    private static BrandDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

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
        List<Brand> brands = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        brands.add(session.get(Brand.class, 1L));
        brands.add(session.get(Brand.class, 2L));
        brands.add(session.get(Brand.class, 3L));
        return brands;
    }
}
