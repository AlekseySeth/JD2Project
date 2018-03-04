package com.nutrition.order;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.user.SystemUser;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order findById(Long id);

    List<Order> findAllByUser(SystemUser systemUser);

    List<Order> findAllByOpenDateBetweenOrderByOpenDateDesc(LocalDateTime from, LocalDateTime to);
}
