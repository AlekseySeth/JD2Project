package dao;

import dao.common.GenericDaoImpl;
import entity.order.Delivery;
import lombok.NoArgsConstructor;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class DeliveryDaoImpl extends GenericDaoImpl<Delivery> {

    private static DeliveryDaoImpl instance;

    public static DeliveryDaoImpl newInstance() {
        if (instance == null) {
            synchronized (DeliveryDaoImpl.class) {
                if (instance == null) {
                    instance = new DeliveryDaoImpl();
                }
            }
        }
        return instance;
    }

}
