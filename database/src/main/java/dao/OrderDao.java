package dao;

import entity.order.Order;
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
public class OrderDao {

    private static OrderDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public static OrderDao newInstance() {
        if (INSTANCE == null) {
            synchronized (OrderDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OrderDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        Session session = SESSION_FACTORY.openSession();
        orders.add(session.get(Order.class, 1000L));
        orders.add(session.get(Order.class, 1001L));
        orders.add(session.get(Order.class, 1002L));
        return orders;
    }

}
