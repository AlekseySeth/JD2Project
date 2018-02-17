package com.nutrition.repository.user;

import com.nutrition.repository.common.GenericDaoImpl;
import com.nutrition.entity.user.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @Override
    public User findByEmail(String email) {
        List<User> resultList = getSessionFactory().getCurrentSession()
                .createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return !resultList.isEmpty() ? resultList.get(0) : null;
    }
}
