package dao;

import entity.marketing.FixedPricePromotion;

/**
 * @author a.shestovsky
 */
public class FixedPricePromotionDao extends GenericDao<FixedPricePromotion> {

    private static FixedPricePromotionDao instance;

    public static FixedPricePromotionDao newInstance() {
        if (instance == null) {
            synchronized (FixedPricePromotionDao.class) {
                if (instance == null) {
                    instance = new FixedPricePromotionDao();
                }
            }
        }
        return instance;
    }

}
