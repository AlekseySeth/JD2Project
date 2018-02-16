package by.nutrition.dao.user;

import by.nutrition.dao.common.GenericDao;
import by.nutrition.entity.user.User;

/**
 * @author a.shestovsky
 */
public interface UserDao extends GenericDao<User> {


    User findByEmail(String email);
}
