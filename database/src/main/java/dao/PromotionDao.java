package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author a.shestovsky
 */
public class PromotionDao {

    private static BrandDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

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
