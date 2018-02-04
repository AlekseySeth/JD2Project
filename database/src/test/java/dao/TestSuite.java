//package dao;
//
//import junit.framework.TestCase;
//import org.hibernate.SessionFactory;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.runner.RunWith;
//import org.junit.runners.Suite;
//import util.SessionFactoryManager;
//
///**
// * @author a.shestovsky
// */
//@RunWith(Suite.class)
//@Suite.SuiteClasses({
//        BrandDaoTest.class,
//        CategoryDaoTest.class,
//        OrderContentDaoTest.class,
//        OrderDaoTest.class,
//        ProductDaoTest.class,
//        PromotionDaoTest.class,
//        UserDaoTest.class
//})
//public class TestSuite extends TestCase {
//
//    protected static SessionFactory sessionFactory;
//
//    @BeforeClass
//    public static void initDb() {
//        sessionFactory = SessionFactoryManager.getSessionFactory();
//    }
//
//    @AfterClass
//    public static void finish() {
//        sessionFactory.close();
//    }
//
//}
