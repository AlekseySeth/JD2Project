package com.nutrition.repository.user;

import com.nutrition.repository.common.GenericDao;
import com.nutrition.entity.user.User;

/**
 * @author a.shestovsky
 */
public interface UserDao extends GenericDao<User> {

    User findByEmail(String email);
}
