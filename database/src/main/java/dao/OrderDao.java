package dao;

import entity.order.Order;
import entity.product.Product;
import entity.user.User;
import lombok.NoArgsConstructor;

import java.util.List;

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
