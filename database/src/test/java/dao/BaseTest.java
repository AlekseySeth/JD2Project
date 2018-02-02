package dao;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import util.SessionFactoryManager;

/**
 * @author a.shestovsky
 */
public abstract class BaseTest {

    protected static SessionFactory sessionFactory;

    @BeforeClass
    public static void initDb() {
        sessionFactory = SessionFactoryManager.getSessionFactory();
        TestDataImporter.newInstance().importTestData(sessionFactory);
    }

    @AfterClass
    public static void finish() {
        sessionFactory.close();
    }
}
