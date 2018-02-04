package dao;

import entity.marketing.FixedPricePromotion;
import entity.marketing.PercentageDiscountPromotion;
import entity.marketing.Promotion;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
public class PromotionDao {

    private static PromotionDao instance;

    public static PromotionDao newInstance() {
        if (instance == null) {
            synchronized (PromotionDao.class) {
                if (instance == null) {
                    instance = new PromotionDao();
                }
            }
        }
        return instance;
    }

    public List<Promotion> getAllPromotions() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Promotion> promotions = session
                .createQuery("select fpp from FixedPricePromotion fpp", Promotion.class).getResultList();
        promotions.addAll(session
                .createQuery("select pdp from PercentageDiscountPromotion pdp", Promotion.class).getResultList());
        session.close();
        return promotions;
    }

    public List<FixedPricePromotion> getAllFixedPricePromotions() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<FixedPricePromotion> fixedPricePromotions = session
                .createQuery("select fpp from FixedPricePromotion fpp", FixedPricePromotion.class)
                .getResultList();
        session.close();
        return fixedPricePromotions;
    }

    public List<PercentageDiscountPromotion> getAllPercentageDiscountPromotions() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<PercentageDiscountPromotion> percentageDiscountPromotions = session
                .createQuery("select pdp from PercentageDiscountPromotion pdp", PercentageDiscountPromotion.class)
                .getResultList();
        session.close();
        return percentageDiscountPromotions;
    }

    public void saveFixedPricePromotion(FixedPricePromotion fixedPricePromotion) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.save(fixedPricePromotion);
        session.close();
    }

    public void savePercentageDiscountPromotion(PercentageDiscountPromotion percentageDiscountPromotion) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.save(percentageDiscountPromotion);
        session.close();
    }

    public FixedPricePromotion getFixedPricePromotion(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        FixedPricePromotion fixedPricePromotion = session.get(FixedPricePromotion.class, id);
        session.close();
        return fixedPricePromotion;
    }

    public PercentageDiscountPromotion getPercentageDiscountPromotion(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        PercentageDiscountPromotion percentageDiscountPromotion = session.get(PercentageDiscountPromotion.class, id);
        session.close();
        return percentageDiscountPromotion;
    }
}
