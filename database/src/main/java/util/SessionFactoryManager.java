package util;

import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author a.shestovsky
 */

public class SessionFactoryManager {

    private static SessionFactory SESSION_FACTORY;

    private SessionFactoryManager() {
    }

    public static SessionFactory getSessionFactory() {
        if (SESSION_FACTORY == null) {
            synchronized (SessionFactory.class) {
                if (SESSION_FACTORY == null) {
                    SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return SESSION_FACTORY;
    }
}
