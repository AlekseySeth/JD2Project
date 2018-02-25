package com.nutrition.product;

import com.nutrition.config.ServiceConfig;
import com.nutrition.entity.product.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

/**
 * @author a.shestovsky
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class BrandServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findAll() throws Exception {
        List<Category> result = categoryService.findAll();
        assertThat(result, hasSize(7));
    }

}