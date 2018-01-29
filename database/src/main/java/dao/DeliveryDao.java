package dao;

import entity.order.Delivery;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class DeliveryDao {

    private static DeliveryDao INSTANCE;

    public static DeliveryDao newInstance() {
        if (INSTANCE == null) {
            synchronized (DeliveryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DeliveryDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Delivery> getAll() {
        return null;
    }

}
