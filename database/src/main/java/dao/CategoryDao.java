package dao;

import entity.product.Category;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class CategoryDao {

    private static CategoryDao INSTANCE;

    public static CategoryDao newInstance() {
        if (INSTANCE == null) {
            synchronized (CategoryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CategoryDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Category> getAll() {
        return null;
    }

}
