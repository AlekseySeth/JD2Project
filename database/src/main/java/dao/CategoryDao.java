package dao;

import entity.product.Category;
import lombok.NoArgsConstructor;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class CategoryDao extends GenericDao<Category> {

    private static CategoryDao instance;

    public static CategoryDao newInstance() {
        if (instance == null) {
            synchronized (CategoryDao.class) {
                if (instance == null) {
                    instance = new CategoryDao();
                }
            }
        }
        return instance;
    }

}
