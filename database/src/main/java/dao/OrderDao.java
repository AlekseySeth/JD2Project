package dao;

import entity.order.Order;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class OrderDao {

    private static OrderDao instance;

    public static OrderDao newInstance() {
        if (instance == null) {
            synchronized (OrderDao.class) {
                if (instance == null) {
                    instance = new OrderDao();
                }
            }
        }
        return instance;
    }

    public List<Order> getAll() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        List<Order> orders = session
                .createQuery("select o from Order o", Order.class)
                .getResultList();
        session.close();
        return orders;
    }

    public void save(Order order) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.save(order);
        session.close();
    }

    public Order get(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        Order order = session.get(Order.class, id);
        session.close();
        return order;
    }
}
