package dao;

import entity.user.User;

/**
 * @author a.shestovsky
 */
public class UserDao {

    public User get(Long id) {
        return new User(id, "Arnold", "Schwarzenegger");
    }
}
