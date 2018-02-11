package dao;

import entity.user.User;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class UserDao extends GenericDao<User> {

    private static UserDao instance;

    public static UserDao newInstance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    public User findByEmail(String email) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        return session.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
