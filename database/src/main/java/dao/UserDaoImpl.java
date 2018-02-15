package dao;

import dao.common.GenericDaoImpl;
import entity.user.User;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import util.SessionFactoryManager;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @Override
    public User findByEmail(String email) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        return session.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
