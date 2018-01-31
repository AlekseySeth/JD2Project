package dao;

import entity.product.Category;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class CategoryDao {

    private static CategoryDao INSTANCE;
    private SessionFactory SESSION_FACTORY;

    public static CategoryDao newInstance() {
        if (INSTANCE == null) {
            synchronized (CategoryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CategoryDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Category> getAll() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        List<Category> categories = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        categories = session.createQuery("select c from Category c").list();
        session.close();
        return categories;
    }

    public void save(Category category) {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        Session session = SESSION_FACTORY.openSession();
        session.save(category);
        session.close();
        SESSION_FACTORY.close();
    }

    public Category get(Long id) {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        Session session = SESSION_FACTORY.openSession();
        Category category = session.get(Category.class, id);
        session.close();
        SESSION_FACTORY.close();
        return category;
    }

}
