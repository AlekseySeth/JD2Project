package dao;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import dao.common.GenericDaoImpl;
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
public class ProductDaoImpl extends GenericDaoImpl<Product> {

    private static ProductDaoImpl instance;

    public static ProductDaoImpl newInstance() {
        if (instance == null) {
            synchronized (ProductDaoImpl.class) {
                if (instance == null) {
                    instance = new ProductDaoImpl();
                }
            }
        }
        return instance;
    }

    public List<Product> searchProducts(Long categoryId, String title, List<Long> brandsId, int limit, int offset) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        JPAQuery<Product> query = new JPAQuery<>(session);
        QProduct product = QProduct.product;
        query.select(product).from(product);
        if (categoryId != null) {
            query.where(product.category.id.eq(categoryId));
        }
        if (title != null) {
            query.where(product.title.like(Expressions.asString("%").concat(title).concat("%")));
        }
        if (brandsId.size() > 0) {
            query.where(product.brand.id.in(brandsId));
        }
        return query.limit(limit).offset(offset).fetchResults().getResults();
    }
}
