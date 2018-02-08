package dao;

import entity.marketing.PercentageDiscountPromotion;

/**
 * @author a.shestovsky
 */
public class PercentageDiscountPromotionDao extends GenericDao<PercentageDiscountPromotion> {

    private static PercentageDiscountPromotionDao instance;

    public static PercentageDiscountPromotionDao newInstance() {
        if (instance == null) {
            synchronized (PercentageDiscountPromotionDao.class) {
                if (instance == null) {
                    instance = new PercentageDiscountPromotionDao();
                }
            }
        }
        return instance;
    }
}
