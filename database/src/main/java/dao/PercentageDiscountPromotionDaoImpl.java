package dao;

import dao.common.GenericDaoImpl;
import entity.marketing.PercentageDiscountPromotion;

/**
 * @author a.shestovsky
 */
public class PercentageDiscountPromotionDaoImpl extends GenericDaoImpl<PercentageDiscountPromotion> {

    private static PercentageDiscountPromotionDaoImpl instance;

    public static PercentageDiscountPromotionDaoImpl newInstance() {
        if (instance == null) {
            synchronized (PercentageDiscountPromotionDaoImpl.class) {
                if (instance == null) {
                    instance = new PercentageDiscountPromotionDaoImpl();
                }
            }
        }
        return instance;
    }
}
