package dao;


import entity.order.Order;
import lombok.NoArgsConstructor;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class OrderDao extends GenericDao<Order> {

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
}
