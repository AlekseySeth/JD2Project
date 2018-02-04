package dao;

import entity.product.Category;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author a.shestovsky
 */
public class CategoryDaoTest {
    @Test
    public void save() throws Exception {
        Category category = new Category("Category_2", "/Category_2");
        CategoryDao categoryDao = CategoryDao.newInstance();
        categoryDao.save(category);
        Session session = ATest.sessionFactory.openSession();
        Category resultSave = session.get(Category.class, 2L);
        session.close();
        assertEquals("Category_2", resultSave.getName());
    }

    @Test
    public void get() throws Exception {
        Category result = CategoryDao.newInstance().get(1L);
        assertEquals("Category", result.getName());
    }
}
