package dao;

import entity.product.Brand;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static dao.TestSuite.sessionFactory;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasSize;

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
    public void findById() throws Exception {
        BrandDao brandDao = BrandDao.newInstance();
        Brand resultGet = brandDao.findById(1L);
        assertEquals("Brand", resultGet.getName());
    }

    public void findAll() {
        BrandDao brandDao = BrandDao.newInstance();
        List<Brand> brands = brandDao.findAll();
        assertThat(brands, hasSize(2));
    }

    public void update() {
        BrandDao brandDao = BrandDao.newInstance();
        Brand brand = brandDao.findById(1L);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        brand.setLogoURL("updated");
        brandDao.update(brand);
        session.getTransaction().commit();
        session.close();
        assertEquals("updated", brand.getLogoURL());
    }

    public void delete() {
        Session session = sessionFactory.openSession();
        Brand brand = new Brand("Brand_3", "/Brand_3");
        session.save(brand);
        session.close();
        BrandDao.newInstance().delete(brand);
        Session secondSession = sessionFactory.openSession();
        Brand result = secondSession.get(Brand.class, 3L);
        assertThat(result, is(nullValue()));
    }
}
