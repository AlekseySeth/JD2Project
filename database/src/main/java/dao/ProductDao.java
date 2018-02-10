package dao;

import com.querydsl.jpa.impl.JPAQuery;
import entity.product.Brand;
import entity.product.Category;
import entity.product.Product;
import entity.product.QProduct;
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

    public List<Product> searchProducts(Category category, String title, List<Brand> brands, int limit, int offset) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        JPAQuery<Product> query = new JPAQuery<>(session);
        QProduct product = QProduct.product;
        query.select(product).from(product);
        if (category != null) {
            query.where(product.category.eq(category));
        }
        if (!title.isEmpty()) {
            query.where(product.title.like(title));
        }
        if (!brands.isEmpty()) {
            query.where(product.brand.in(brands));
        }
        return query.limit(limit).offset(offset).fetchResults().getResults();
    }
}
