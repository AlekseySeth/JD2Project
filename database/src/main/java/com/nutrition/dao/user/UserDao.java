package com.nutrition.dao.user;

import com.nutrition.dao.common.GenericDao;
import com.nutrition.entity.user.User;

/**
 * @author a.shestovsky
 */
public interface UserDao extends GenericDao<User> {

    User findByEmail(String email);
}
