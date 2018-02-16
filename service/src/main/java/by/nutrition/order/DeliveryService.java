package by.nutrition.order;

import by.nutrition.entity.order.Delivery;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface DeliveryService {

    Delivery findById(Long id);

    List<Delivery> findAll();
}
