package dao;

import entity.user.User;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class UserDao {

    private static UserDao INSTANCE;

    public static UserDao newInstance() {
        if (INSTANCE == null) {
            synchronized (UserDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<User> getAll() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<User> users = session
                .createQuery("select u from User u", User.class)
                .getResultList();
        session.close();
        return users;
    }

    public void save(User user) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.save(user);
        session.close();
    }

    public User get(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }
}
