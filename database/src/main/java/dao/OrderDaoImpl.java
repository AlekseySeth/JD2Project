package dao;


import dao.common.GenericDaoImpl;
import entity.order.Order;
import lombok.NoArgsConstructor;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class OrderDaoImpl extends GenericDaoImpl<Order> {

    private static OrderDaoImpl instance;

    public static OrderDaoImpl newInstance() {
        if (instance == null) {
            synchronized (OrderDaoImpl.class) {
                if (instance == null) {
                    instance = new OrderDaoImpl();
                }
            }
        }
        return instance;
    }
}
