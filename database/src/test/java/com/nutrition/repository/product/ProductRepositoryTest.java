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
    public void findByTitleContaining() {
        Product result = productRepository.findByTitleContaining("carbox");
        assertThat(result.getTitle(), equalTo("Biotech CARBOX 1000"));
    }

    @Test
    public void findByTitleCategoryBrandsViaId() {
        List<Product> resultByTitle = productRepository
                .findByTitleCategoryBrandsViaId("xplode", null, new ArrayList<>());
        assertThat(resultByTitle, hasSize(2));
        assertThat(resultByTitle.get(0).getTitle(), equalTo("BCAA Xplode 280 g"));

        List<Product> resultByCategory = productRepository
                .findByTitleCategoryBrandsViaId(null, 7L, new ArrayList<>());
        assertThat(resultByCategory, hasSize(4));

        List<Product> resultByBrands = productRepository
                .findByTitleCategoryBrandsViaId(null, null, Arrays.asList(4L, 5L));
        assertThat(resultByBrands, hasSize(5));
    }
}