package dao;

import entity.product.Brand;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author a.shestovsky
 */
public class BrandDaoTest extends BaseTest {
    @Test
    public void save() throws Exception {
        Brand brand = new Brand("testBrand_1", "/testBrand_1");
        BrandDao brandDao = BrandDao.newInstance();
        brandDao.save(brand);
        Session session = SESSION_FACTORY.openSession();
        Brand resultSave = session.get(Brand.class, 2L);
        session.close();
        assertEquals("testBrand_1", resultSave.getName());
    }

    @Test
    public void get() throws Exception {
        Brand brand = new Brand("testBrand_2", "/testBrand_2");
        Session session = SESSION_FACTORY.openSession();
        session.save(brand);
        session.close();
        BrandDao brandDao = BrandDao.newInstance();
        Brand resultGet = brandDao.get(1L);
        assertEquals("testBrand_2", resultGet.getName());
    }
}
