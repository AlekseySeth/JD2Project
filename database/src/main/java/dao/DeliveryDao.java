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
    private SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

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
        List<Delivery> deliveries = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        deliveries.add(session.get(Delivery.class, 1L));
        deliveries.add(session.get(Delivery.class, 2L));
        deliveries.add(session.get(Delivery.class, 3L));
        return deliveries;
    }

}
