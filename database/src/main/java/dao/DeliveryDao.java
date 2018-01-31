package dao;

import entity.order.Delivery;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class DeliveryDao {

    private static DeliveryDao INSTANCE;
    private SessionFactory SESSION_FACTORY;

    public static DeliveryDao newInstance() {
        if (INSTANCE == null) {
            synchronized (DeliveryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DeliveryDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Delivery> getAll() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        List<Delivery> deliveries = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        deliveries = session.createQuery("select d from deliveries d").list();
        session.close();
        return deliveries;
    }

    public void save(Delivery delivery) {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        Session session = SESSION_FACTORY.openSession();
        session.save(delivery);
        session.close();
        SESSION_FACTORY.close();
    }

    public Delivery get(Long id) {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        Session session = SESSION_FACTORY.openSession();
        Delivery delivery = session.get(Delivery.class, id);
        session.close();
        SESSION_FACTORY.close();
        return delivery;
    }

}
