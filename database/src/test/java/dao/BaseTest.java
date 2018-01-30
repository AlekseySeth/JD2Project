package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author a.shestovsky
 */
public abstract class BaseTest {

    protected static SessionFactory SESSION_FACTORY;

    @BeforeClass
    public static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @AfterClass
    public static void finish() {
        SESSION_FACTORY.close();
    }
}
