package com.nutrition.repository.order;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.user.SystemUser;
import com.nutrition.entity.user.SystemUser;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author a.shestovsky
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByUser(SystemUser systemUser);

    List<Order> findAllByOpenDateBetweenOrderByOpenDateDesc(LocalDateTime from, LocalDateTime to);
}
