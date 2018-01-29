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
    private SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

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
        List<Category> categories = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        categories.add(session.get(Category.class, 1L));
        categories.add(session.get(Category.class, 2L));
        categories.add(session.get(Category.class, 3L));
        return categories;
    }

}
