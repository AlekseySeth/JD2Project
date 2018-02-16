package by.nutrition.dao.user;

import by.nutrition.dao.common.GenericDaoImpl;
import by.nutrition.entity.user.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @Override
    public User findByEmail(String email) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
