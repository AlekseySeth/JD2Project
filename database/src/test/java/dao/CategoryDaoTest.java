package dao;

import entity.product.Category;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author a.shestovsky
 */
public class CategoryDaoTest extends BaseTest {
    @Test
    public void save() throws Exception {
        Category category = new Category("testCategory_1", "/testCategory_1");
        CategoryDao categoryDao = CategoryDao.newInstance();
        categoryDao.save(category);
        Session session = SESSION_FACTORY.openSession();
        Category resultSave = session.get(Category.class, 1L);
        session.close();
        assertEquals("testCategory_1", resultSave.getName());
    }

    @Test
    public void get() throws Exception {
        Category category = new Category("testCategory_2", "/testCategory_2");
        Session session = SESSION_FACTORY.openSession();
        session.save(category);
        session.close();
        CategoryDao categoryDao = CategoryDao.newInstance();
        Category result = categoryDao.get(1L);
        assertEquals("testCategory_2", result.getName());
    }

}
