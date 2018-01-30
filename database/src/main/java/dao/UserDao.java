package dao;

import entity.user.User;
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
public class UserDao {

    private static UserDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

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
        List<User> users = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        users.add(session.get(User.class, 1L));
        users.add(session.get(User.class, 2L));
        users.add(session.get(User.class, 3L));
        session.close();
        SESSION_FACTORY.close();
        return users;
    }

    public void save(User user) {
        Session session = SESSION_FACTORY.openSession();
        session.save(user);
        session.close();
        SESSION_FACTORY.close();
    }

    public User get(Long id) {
        Session session = SESSION_FACTORY.openSession();
        User user = session.get(User.class, id);
        session.close();
        SESSION_FACTORY.close();
        return user;
    }

}
