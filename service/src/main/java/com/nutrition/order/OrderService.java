package com.nutrition.order;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.order.OrderContent;
import com.nutrition.entity.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order findById(Long id);

    List<Order> findAllByUser(User user);

    List<Order> findAllByOpenDateBetweenOrderByOpenDateDesc(LocalDateTime from, LocalDateTime to);

    boolean addProductToCart(Order order, OrderContent orderContent);

    void removeProductFromCart(Order order, Long productId, Integer productQty);

    void setOrderDelivery(Order order, Long deliveryId);

    BigDecimal calculateSubtotalPrice(Order order);

    BigDecimal calculateTotalPrice(Order order);

    Order placeOrder(Order order);

    Order createInitialOrder(User userFromSession);
}
