package dao;

import entity.product.Brand;
import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

/**
 * @author a.shestovsky
 */
public class BrandDaoTest extends BaseTest {
    @Test
    public void saveAndGetFromDb() throws Exception {
        Brand brand = new Brand("test", "/test");
        BrandDao brandDao = BrandDao.newInstance();
        brandDao.save(brand);

        Session session = SESSION_FACTORY.openSession();
        Brand result = session.get(Brand.class, 1L);
        session.close();
        assertEquals("test", result.getName());
    }

    @Test
    public void get() throws Exception {
        Brand brand = new Brand("test", "/test");
        Session session = SESSION_FACTORY.openSession();
        session.save(brand);
        session.close();

        BrandDao brandDao = BrandDao.newInstance();
        Brand result = brandDao.get(1L);
        assertEquals("test", result.getName());
    }

}

