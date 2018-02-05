package dao;

import entity.marketing.FixedPricePromotion;
import org.hibernate.Session;
import org.junit.Test;

import java.math.BigDecimal;

import static dao.TestSuite.sessionFactory;
import static org.junit.Assert.*;

/**
 * @author a.shestovsky
 */
public class FixedPricePromotionDaoTest {
    @Test
    public void saveFixedPricePromotion() throws Exception {
        FixedPricePromotion fixedPricePromotion = new FixedPricePromotion("FixedPricePromo_2", false, new BigDecimal(10));
        PromotionDao.newInstance().saveFixedPricePromotion(fixedPricePromotion);
        Session session = sessionFactory.openSession();
        FixedPricePromotion result = session.get(FixedPricePromotion.class, 3L);
        session.close();
        assertEquals("FixedPricePromo_2", result.getName());
    }

    @Test
    public void getFixedPricePromotion() throws Exception {
        FixedPricePromotion result = PromotionDao.newInstance().getFixedPricePromotion(1L);
        assertEquals("FixedPricePromo", result.getName());
    }

}