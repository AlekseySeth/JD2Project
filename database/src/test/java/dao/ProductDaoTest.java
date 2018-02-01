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
public class ProductDaoTest extends BaseTest {
    @Test
    public void save() throws Exception {
        Brand brand = new Brand("Brand", "/Brand");
        BrandDao.newInstance().save(brand);

        Category category = new Category("Category", "/Category");
        CategoryDao.newInstance().save(category);

        Product product = new Product("Product", "Description", new BigDecimal(10.0),
                10, category, brand, "image");
        ProductDao.newInstance().save(product);

        Session session = SESSION_FACTORY.openSession();
        Product result = session.get(Product.class, 1L);

        String title = result.getTitle();
        String brandName = result.getBrand().getName();
        String categoryName = result.getCategory().getName();

        session.close();

        assertEquals("Product", title);
        assertEquals("Brand", brandName);
        assertEquals("Category", categoryName);
    }

    @Test
    public void get() throws Exception {
    }

}
