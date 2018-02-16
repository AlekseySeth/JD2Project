package by.nutrition.dao.marketing;

import by.nutrition.dao.common.GenericDaoImpl;
import by.nutrition.entity.marketing.Promotion;
import org.hibernate.Session;

import java.util.List;

/**
 * @author a.shestovsky
 */
public class PromotionDaoImpl extends GenericDaoImpl<Promotion> {

    public List<Promotion> findAllPromotions() {
        Session currentSession = getSessionFactory().getCurrentSession();
        List<Promotion> promotions = currentSession.createQuery("select fpp from FixedPricePromotion fpp", Promotion.class).getResultList();
        promotions.addAll(currentSession
                .createQuery("select pdp from PercentageDiscountPromotion pdp", Promotion.class).getResultList());
        return promotions;
    }
}
