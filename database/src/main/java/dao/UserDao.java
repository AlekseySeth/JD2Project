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
        return users;
    }

}
