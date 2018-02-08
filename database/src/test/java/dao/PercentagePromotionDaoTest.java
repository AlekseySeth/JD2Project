package dao;

import entity.marketing.FixedPricePromotion;
import entity.marketing.PercentageDiscountPromotion;
import entity.marketing.Promotion;
import org.hibernate.Session;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static dao.TestSuite.*;
import static org.junit.Assert.assertEquals;

/**
 * @author a.shestovsky
 */
public class PercentagePromotionDaoTest {

    @Test
    public void findAllPromotions() {
        List<Promotion> allPromotions = PromotionDao.newInstance().findAllPromotions();
        assertEquals(false, allPromotions.get(0).isActive());
    }

    @Test
    public void savePercentageDiscountPromotion() throws Exception {
        PercentageDiscountPromotion percentageDiscountPromotion = new PercentageDiscountPromotion("PercentageDiscountPromo_2", true, 25);
        PercentageDiscountPromotionDao.newInstance().save(percentageDiscountPromotion);
        Session session = sessionFactory.openSession();
        PercentageDiscountPromotion result = session.get(PercentageDiscountPromotion.class, 4L);
        session.close();
        assertEquals("PercentageDiscountPromo_2", result.getName());
//        assertEquals(25, result.getDiscountValue());
    }

    @Test
    public void getPercentageDiscountPromotion() throws Exception {
        PercentageDiscountPromotion result = PercentageDiscountPromotionDao.newInstance().findById(2L);
        assertEquals("PercentageDiscountPromo", result.getName());
        assertEquals(50, result.getDiscountValue());
    }
}