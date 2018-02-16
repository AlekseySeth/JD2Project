package by.nutrition.dao.product;

import by.nutrition.dao.common.GenericDaoImpl;
import by.nutrition.entity.product.Product;
import by.nutrition.entity.product.QProduct;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

    @Override
    public List<Product> searchProducts(Long categoryId, String title, List<Long> brandsId, int limit, int offset) {
        Session session = getSessionFactory().getCurrentSession();
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
