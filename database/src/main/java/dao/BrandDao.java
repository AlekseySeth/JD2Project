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
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Brand> brands = session.createQuery("select b from Brand b", Brand.class).getResultList();
        session.close();
        return brands;
    }

    public void save(Brand brand) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.save(brand);
        session.close();
    }

    public Brand get(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        Brand brand = session.get(Brand.class, id);
        session.close();
        return brand;
    }
}
