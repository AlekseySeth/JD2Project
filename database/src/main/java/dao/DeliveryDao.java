package dao;

import entity.order.Delivery;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class DeliveryDao {

    private static DeliveryDao instance;

    public static DeliveryDao newInstance() {
        if (instance == null) {
            synchronized (DeliveryDao.class) {
                if (instance == null) {
                    instance = new DeliveryDao();
                }
            }
        }
        return instance;
    }

    public List<Delivery> getAll() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Delivery> deliveries = session
                .createQuery("select d from Delivery d", Delivery.class)
                .getResultList();
        session.close();
        return deliveries;
    }

    public void save(Delivery delivery) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.save(delivery);
        session.close();
    }

    public Delivery get(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        Delivery delivery = session.get(Delivery.class, id);
        session.close();
        return delivery;
    }
}
