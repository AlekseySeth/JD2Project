package dao;

import entity.order.Order;
import entity.order.OrderContent;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
public class OrderContentDao extends GenericDao<OrderContent> {

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

}
