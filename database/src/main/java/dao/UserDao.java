package dao;

import dao.common.GenericDao;
import entity.user.User;

/**
 * @author a.shestovsky
 */
public interface UserDao extends GenericDao<User> {


    User findByEmail(String email);
}
