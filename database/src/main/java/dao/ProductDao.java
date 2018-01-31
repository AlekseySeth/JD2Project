package dao;

import entity.product.Product;
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
public class ProductDao {

    private static ProductDao INSTANCE;
    private SessionFactory SESSION_FACTORY;

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
        List<Product> products = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        products = session.createQuery("select p from Product p").list();
        session.close();
        SESSION_FACTORY.close();
        return products;
    }

    public void save(Product product) {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        Session session = SESSION_FACTORY.openSession();
        session.save(product);
        session.close();
        SESSION_FACTORY.close();
    }

    public Product get(Long id) {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        Session session = SESSION_FACTORY.openSession();
        Product product = session.get(Product.class, id);
        session.close();
        SESSION_FACTORY.close();
        return product;
    }

}
