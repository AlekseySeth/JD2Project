package dao;

import dao.common.GenericDaoImpl;
import entity.product.Brand;
import lombok.NoArgsConstructor;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class BrandDaoImpl extends GenericDaoImpl<Brand> {

    private static BrandDaoImpl instance;

    public static BrandDaoImpl newInstance() {
        if (instance == null) {
            synchronized (CategoryDaoImpl.class) {
                if (instance == null) {
                    instance = new BrandDaoImpl();
                }
            }
        }
        return instance;
    }

}
