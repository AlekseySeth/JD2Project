package dao;

import entity.product.Category;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author a.shestovsky
 */
public class CategoryDaoTest extends BaseTest {
    @Test
    public void save() throws Exception {
    }

    @Test
    public void get() throws Exception {
        Category category = new Category("test", "/test");
        Session session = SESSION_FACTORY.openSession();

        session.save(category);

        session.close();
        SESSION_FACTORY.close();

        CategoryDao categoryDao = CategoryDao.newInstance();
        Category result = categoryDao.get(1L);
        assertEquals("test", result.getName());
    }

}
