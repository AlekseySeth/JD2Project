package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author a.shestovsky
 */

public final class SessionFactoryManager {

    private static SessionFactory sessionFactory;

    private SessionFactoryManager() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (SessionFactory.class) {
                if (sessionFactory == null) {
                    sessionFactory = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }
}
