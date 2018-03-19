package com.nutrition.repository.product;

import com.nutrition.entity.product.Product;
import com.nutrition.entity.product.QProduct;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Product> findProductsByFilter(String title, Long categoryId, List<Long> brandIds,
                                              int productsOnPage, int offset) {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct product = QProduct.product;
        query.select(product).from(product);
        if (title != null && !"".equals(title)) {
            query.where(product.title.like(Expressions.asString("%").concat(title).concat("%")));
        }
        if (categoryId != null) {
            query.where(product.category.id.eq(categoryId));
        }
        if (brandIds != null && brandIds.size() > 0) {
            query.where(product.brand.id.in(brandIds));
        }
        return query.limit(productsOnPage).offset(offset).fetchResults().getResults();
    }

    @Override
    public long countProductsByFilter(String title, Long categoryId, List<Long> brandIds) {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct product = QProduct.product;
        query.select(product).from(product);
        if (title != null && !"".equals(title)) {
            query.where(product.title.like(Expressions.asString("%").concat(title).concat("%")));
        }
        if (categoryId != null) {
            query.where(product.category.id.eq(categoryId));
        }
        if (brandIds != null && brandIds.size() > 0) {
            query.where(product.brand.id.in(brandIds));
        }
        return query.fetchCount();
    }
}
