package dao;

import entity.product.Brand;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class BrandDao {

    private static BrandDao INSTANCE;
    private SessionFactory SESSION_FACTORY;

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
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

        List<Brand> brands = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        brands = session.createQuery("select b from Brand b").list();
        session.close();
        return brands;
    }

    public void save(Brand brand) {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        Session session = SESSION_FACTORY.openSession();
        session.save(brand);
        session.close();
        SESSION_FACTORY.close();
    }

    public Brand get(Long id) {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        Session session = SESSION_FACTORY.openSession();
        Brand brand = session.get(Brand.class, id);
        session.close();
        SESSION_FACTORY.close();
        return brand;
    }
}
