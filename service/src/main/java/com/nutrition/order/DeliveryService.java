package com.nutrition.order;

import com.nutrition.entity.order.Delivery;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface DeliveryService {

    Delivery findById(Long id);

    List<Delivery> findAll();
}
