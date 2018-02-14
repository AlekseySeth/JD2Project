package dao;

import dao.common.GenericDaoImpl;
import entity.user.User;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class UserDaoImpl extends GenericDaoImpl<User> {

    private static UserDaoImpl instance;

    public static UserDaoImpl newInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
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
