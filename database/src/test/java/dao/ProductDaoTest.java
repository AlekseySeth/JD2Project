package dao;

import entity.product.Brand;
import entity.product.Category;
import entity.product.Product;
import org.hibernate.Session;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author a.shestovsky
 */
public class ProductDaoTest {
    @Test
    public void save() throws Exception {
        Session session = TestSuite.sessionFactory.openSession();
        Brand brand = session.get(Brand.class, 1L);
        Category category = session.get(Category.class, 1L);

        Product product = new Product("Product_2", "Description_2", new BigDecimal(10.0),
                10, category, brand, null, "image_2");
        ProductDao.newInstance().save(product);

        Product result = session.get(Product.class, 2L);
        session.close();

        assertEquals("Product_2", result.getTitle());
        assertEquals("Brand", result.getBrand().getName());
        assertEquals("Category", result.getCategory().getName());
    }

    @Test
    public void get() throws Exception {
        Product product = ProductDao.newInstance().findById(1L);
        assertEquals("Product", product.getTitle());
        assertEquals("Brand", product.getBrand().getName());
        assertEquals("Category", product.getCategory().getName());
        assertEquals("PercentageDiscountPromo", product.getPromotion().getName());
    }
}
