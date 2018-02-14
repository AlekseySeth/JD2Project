package dao;

import dao.common.GenericDaoImpl;
import entity.order.OrderContent;

/**
 * @author a.shestovsky
 */
public class OrderContentDaoImpl extends GenericDaoImpl<OrderContent> {

    private static OrderContentDaoImpl instance;

    public static OrderContentDaoImpl newInstance() {
        if (instance == null) {
            synchronized (OrderContentDaoImpl.class) {
                if (instance == null) {
                    instance = new OrderContentDaoImpl();
                }
            }
        }
        return instance;
    }

}
