package dao;

import entity.user.User;
import lombok.NoArgsConstructor;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class UserDao extends GenericDao<User> {

    private static UserDao instance;

    public static UserDao newInstance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    public User findByEmai(String email) {

        return null;
    }

}
