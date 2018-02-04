package dao;

import entity.marketing.FixedPricePromotion;
import entity.marketing.PercentageDiscountPromotion;
import entity.marketing.Promotion;
import org.hibernate.Session;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author a.shestovsky
 */
public class PromotionDaoTest {

    @Test
    public void getAllPromotions() {
        List<Promotion> allPromotions = PromotionDao.newInstance().getAllPromotions();
        assertEquals(false, allPromotions.get(0).isActive());
    }

    @Test
    public void saveFixedPricePromotion() throws Exception {
        FixedPricePromotion fixedPricePromotion = new FixedPricePromotion("FixedPricePromo_2", false, new BigDecimal(10));
        PromotionDao.newInstance().saveFixedPricePromotion(fixedPricePromotion);
        Session session = ATest.sessionFactory.openSession();
        FixedPricePromotion result = session.get(FixedPricePromotion.class, 3L);
        session.close();
        assertEquals("FixedPricePromo_2", result.getName());
    }

    @Test
    public void savePercentageDiscountPromotion() throws Exception {
        PercentageDiscountPromotion percentageDiscountPromotion = new PercentageDiscountPromotion("PercentageDiscountPromo_2", true, 25);
        PromotionDao.newInstance().savePercentageDiscountPromotion(percentageDiscountPromotion);
        Session session = ATest.sessionFactory.openSession();
        PercentageDiscountPromotion result = session.get(PercentageDiscountPromotion.class, 4L);
        session.close();
        assertEquals("PercentageDiscountPromo_2", result.getName());
//        assertEquals(25, result.getDiscountValue());
    }

    @Test
    public void getFixedPricePromotion() throws Exception {
        FixedPricePromotion result = PromotionDao.newInstance().getFixedPricePromotion(1L);
        assertEquals("FixedPricePromo", result.getName());
    }

    @Test
    public void getPercentageDiscountPromotion() throws Exception {
        PercentageDiscountPromotion result = PromotionDao.newInstance().getPercentageDiscountPromotion(2L);
        assertEquals("PercentageDiscountPromo", result.getName());
        assertEquals(50, result.getDiscountValue());
    }
}