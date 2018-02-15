package dao;

import dao.common.GenericDaoImpl;
import entity.marketing.Promotion;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
public class PromotionDaoImpl extends GenericDaoImpl<Promotion> {

    public List<Promotion> findAllPromotions() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Promotion> promotions = session
                .createQuery("select fpp from FixedPricePromotion fpp", Promotion.class).getResultList();
        promotions.addAll(session
                .createQuery("select pdp from PercentageDiscountPromotion pdp", Promotion.class).getResultList());
        session.close();
        return promotions;
    }
}
