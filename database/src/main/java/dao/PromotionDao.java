package dao;

import org.hibernate.SessionFactory;

/**
 * @author a.shestovsky
 */
public class PromotionDao {

    private static BrandDao INSTANCE;
    private SessionFactory SESSION_FACTORY;

    public static BrandDao newInstance() {
        if (INSTANCE == null) {
            synchronized (CategoryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BrandDao();
                }
            }
        }
        return INSTANCE;
    }

}
