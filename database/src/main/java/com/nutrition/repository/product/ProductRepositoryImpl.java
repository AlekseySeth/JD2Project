package com.nutrition.repository.product;

import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import com.nutrition.entity.product.QProduct;
import com.nutrition.entity.util.IdentifiableEntity;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Product> findByTitleCategoryBrands(String title, Category category, List<Brand> brands) {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct product = QProduct.product;
        query.select(product).from(product);
        if (title != null) {
            query.where(product.title.like(Expressions.asString("%").concat(title).concat("%")));
        }
        if (category != null) {
            query.where(product.category.id.eq(category.getId()));
        }
        if (brands.size() > 0) {
            List<Long> brandIds = brands.stream().map(IdentifiableEntity::getId).collect(Collectors.toList());
            query.where(product.brand.id.in(brandIds));
        }
        return query.fetchResults().getResults();
    }

    @Override
    public List<Product> findByTitleCategoryBrandsViaId(String title, Long categoryId, List<Long> brandIds) {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct product = QProduct.product;
        query.select(product).from(product);
        if (title != null) {
            query.where(product.title.like(Expressions.asString("%").concat(title).concat("%")));
        }
        if (categoryId != null) {
            query.where(product.category.id.eq(categoryId));
        }
        if (brandIds.size() > 0) {
            query.where(product.brand.id.in(brandIds));
        }
        return query.fetchResults().getResults();
    }
}
