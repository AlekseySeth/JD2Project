package com.nutrition.repository.order;

import com.nutrition.entity.order.Delivery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    List<Delivery> findAll();
}
