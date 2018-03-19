package com.nutrition.repository.product;

import com.nutrition.config.PersistenceTestConfig;
import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceTestConfig.class)
public class ProductRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void findAllBrands() throws Exception {
        List<Brand> result = brandRepository.findAll();
        assertThat(result, hasSize(5));
    }

    @Test
    public void findAllCategories() {
        List<Category> result = categoryRepository.findAll();
        assertThat(result, hasSize(7));
    }

    @Test
    public void findByTitleCategoryBrandsViaId() {
        List<Product> resultByTitle = productRepository
                .findProductsByFilter("xplode", null, new ArrayList<>(), 10, 0);
        assertThat(resultByTitle, hasSize(2));
        assertThat(resultByTitle.get(0).getTitle(), equalTo("BCAA Xplode 280 g"));

        List<Product> resultByCategory = productRepository
                .findProductsByFilter(null, 7L, new ArrayList<>(), 10, 0);
        assertThat(resultByCategory, hasSize(4));

        List<Product> resultByBrands = productRepository
                .findProductsByFilter(null, null, Arrays.asList(4L, 5L), 10, 0);
        assertThat(resultByBrands, hasSize(5));
    }

    @Test
    public void countProductsByFilter() throws Exception {
        long productsCount = productRepository.countProductsByFilter(null, 1L, null);
        assertThat(productsCount, equalTo(7L));
    }
}