package dao;

import entity.order.Order;
import entity.product.Product;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class OrderDao {

    private static OrderDao INSTANCE;

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
