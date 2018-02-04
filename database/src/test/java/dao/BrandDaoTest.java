package dao;

import entity.product.Brand;
import org.hibernate.Session;
import org.junit.Test;

import static dao.ATest.sessionFactory;
import static org.junit.Assert.assertEquals;

/**
 * @author a.shestovsky
 */

public class BrandDaoTest {

    @Test
    public void save() throws Exception {
        Brand brand = new Brand("Brand_2", "/Brand_2");
        BrandDao brandDao = BrandDao.newInstance();
        brandDao.save(brand);
        Session session = sessionFactory.openSession();
        Brand resultSave = session.get(Brand.class, 2L);
        session.close();
        assertEquals("Brand_2", resultSave.getName());
    }

    @Test
    public void get() throws Exception {
        BrandDao brandDao = BrandDao.newInstance();
        Brand resultGet = brandDao.get(1L);
        assertEquals("Brand", resultGet.getName());
    }
}
