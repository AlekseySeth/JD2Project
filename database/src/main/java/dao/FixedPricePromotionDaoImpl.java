package dao;

import dao.common.GenericDaoImpl;
import entity.marketing.FixedPricePromotion;

/**
 * @author a.shestovsky
 */
public class FixedPricePromotionDaoImpl extends GenericDaoImpl<FixedPricePromotion> {

    private static FixedPricePromotionDaoImpl instance;

    public static FixedPricePromotionDaoImpl newInstance() {
        if (instance == null) {
            synchronized (FixedPricePromotionDaoImpl.class) {
                if (instance == null) {
                    instance = new FixedPricePromotionDaoImpl();
                }
            }
        }
        return instance;
    }

}
