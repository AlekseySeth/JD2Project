package by.nutrition.dao;

import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author a.shestovsky
 */
//@RunWith(Suite.class)
//@Suite.SuiteClasses({
////        BrandDaoTest.class,
////        UserDaoTest.class
//})
//public class TestSuite extends TestCase {
//
//    protected static SessionFactory sessionFactory;
//
//    @BeforeClass
//    public static void initDb() {
//        getSessionFactory();
//        TestDataImporter.newInstance().importTestData(sessionFactory);
//    }
//
//    @AfterClass
//    public static void finish() {
//        sessionFactory.close();
//    }
//}