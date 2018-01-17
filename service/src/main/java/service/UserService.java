package service;

import dao.UserDao;
import entity.user.User;

/**
 * @author a.shestovsky
 */
public class UserService {

    public User getUserById(Long id) {
        UserDao userDao = new UserDao();
        return userDao.get(id);
    }
}