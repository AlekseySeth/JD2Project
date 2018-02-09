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
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Product> productsByCategory = session
                .createQuery("select p from Product where p.category = :productCategory", Product.class)
                .setParameter("productCategory", category)
                .getResultList();
        session.close();
        return productsByCategory;
    }

    public List<Product> findByBrand(Brand brand) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Product> productsByBrand = session
                .createQuery("select p from Product where p.brand = :productBrand", Product.class)
                .setParameter("productBrand", brand)
                .getResultList();
        session.close();
        return productsByBrand;
    }

    public List<Product> findByTitle(String title) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Product> products = session
                .createQuery("select p from Product where p.title like %:productTitle%", Product.class)
                .setParameter(":productTitle", title)
                .getResultList();
        session.close();
        return products;
    }
}
