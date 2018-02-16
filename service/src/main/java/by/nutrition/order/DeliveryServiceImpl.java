package by.nutrition.order;

import by.nutrition.dao.order.DeliveryDao;
import by.nutrition.entity.order.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author a.shestovsky
 */

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryDao deliveryDao;

    @Autowired
    public DeliveryServiceImpl(DeliveryDao deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

    @Override
    public Delivery findById(Long id) {
        return deliveryDao.findById(id);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryDao.findAll();
    }
}
