package dao;

import entity.order.OrderContent;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
public class OrderContentDao {

    private static OrderContentDao instance;

    public static OrderContentDao newInstance() {
        if (instance == null) {
            synchronized (OrderContentDao.class) {
                if (instance == null) {
                    instance = new OrderContentDao();
                }
            }
        }
        return instance;
    }

    public List<OrderContent> getByOrder(Long orderId) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        return session.createQuery("select oc from OrderContent oc where oc.order.id = :orderId", OrderContent.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }


    public void save(OrderContent orderContent) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.save(orderContent);
        session.close();
    }

    public OrderContent get(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        OrderContent orderContent = session.get(OrderContent.class, id);
        session.close();
        return orderContent;
    }
}
