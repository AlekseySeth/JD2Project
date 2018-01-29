package dao;

import entity.user.User;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class UserDao {

    private static UserDao INSTANCE;

    public static UserDao newInstance() {
        if (INSTANCE == null) {
            synchronized (UserDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<User> getAll() {
        return null;
    }

}
