package dao;

import entity.order.Order;
import lombok.NoArgsConstructor;

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
        return null;
    }

}
