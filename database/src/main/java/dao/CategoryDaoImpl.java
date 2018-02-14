package dao;

import dao.common.GenericDaoImpl;
import entity.product.Category;
import lombok.NoArgsConstructor;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class CategoryDaoImpl extends GenericDaoImpl<Category> {

    private static CategoryDaoImpl instance;

    public static CategoryDaoImpl newInstance() {
        if (instance == null) {
            synchronized (CategoryDaoImpl.class) {
                if (instance == null) {
                    instance = new CategoryDaoImpl();
                }
            }
        }
        return instance;
    }

}
