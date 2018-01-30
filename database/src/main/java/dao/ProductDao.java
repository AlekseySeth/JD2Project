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
    private SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

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
        products.add(session.get(Product.class, 1000L));
        products.add(session.get(Product.class, 1001L));
        products.add(session.get(Product.class, 1002L));
        session.close();
        SESSION_FACTORY.close();
        return products;
    }

    public void save(Product product) {
        Session session = SESSION_FACTORY.openSession();
        session.save(product);
        session.close();
        SESSION_FACTORY.close();
    }

    public Product get(Long id) {
        Session session = SESSION_FACTORY.openSession();
        Product product = session.get(Product.class, id);
        session.close();
        SESSION_FACTORY.close();
        return product;
    }

}
