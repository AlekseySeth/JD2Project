package dao;

import entity.product.Category;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class CategoryDao {

    private static CategoryDao instance;

    public static CategoryDao newInstance() {
        if (instance == null) {
            synchronized (CategoryDao.class) {
                if (instance == null) {
                    instance = new CategoryDao();
                }
            }
        }
        return instance;
    }

    public List<Category> getAll() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Category> categories = session
                .createQuery("select c from Category c", Category.class)
                .getResultList();
        session.close();
        return categories;
    }

    public void save(Category category) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.save(category);
        session.close();
    }

    public Category get(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }
}
