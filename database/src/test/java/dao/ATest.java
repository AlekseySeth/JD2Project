package dao;

import org.hibernate.SessionFactory;
import org.junit.Test;
import util.SessionFactoryManager;

/**
 * @author a.shestovsky
 */

public class ATest {

    protected static SessionFactory sessionFactory = SessionFactoryManager.getSessionFactory();

    @Test
    public void initDb() {
        sessionFactory = SessionFactoryManager.getSessionFactory();
        TestDataImporter.newInstance().importTestData(sessionFactory);
    }

}
