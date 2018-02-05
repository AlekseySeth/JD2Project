package dao;

import entity.product.Product;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class ProductDao {

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

    public List<Product> getAll() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Product> products = session
                .createQuery("select p from Product p", Product.class)
                .getResultList();
        session.close();
        return products;
    }

    public void save(Product product) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.save(product);
        session.close();
    }

    public Product get(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }
}
