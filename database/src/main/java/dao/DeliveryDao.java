package dao;

import entity.order.Delivery;
import lombok.NoArgsConstructor;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class DeliveryDao extends GenericDao<Delivery> {

    private static DeliveryDao instance;

    public static DeliveryDao newInstance() {
        if (instance == null) {
            synchronized (DeliveryDao.class) {
                if (instance == null) {
                    instance = new DeliveryDao();
                }
            }
        }
        return instance;
    }

}
