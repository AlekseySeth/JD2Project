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
        orders = session.createQuery("select o from orders o").list();
        session.close();
        return orders;
    }

    public void save(Order order) {
        Session session = SESSION_FACTORY.openSession();
        session.save(order);
        session.close();
        SESSION_FACTORY.close();
    }

    public Order get(Long id) {
        Session session = SESSION_FACTORY.openSession();
        Order order = session.get(Order.class, id);
        session.close();
        SESSION_FACTORY.close();
        return order;
    }

}
