package com.nutrition.repository.order;

import com.nutrition.entity.order.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * @author a.shestovsky
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}
