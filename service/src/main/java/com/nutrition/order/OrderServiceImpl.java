package com.nutrition.order;

import com.nutrition.entity.order.Delivery;
import com.nutrition.entity.order.Order;
import com.nutrition.entity.order.OrderContent;
import com.nutrition.entity.order.Status;
import com.nutrition.entity.user.User;
import com.nutrition.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final long DEFAULT_DELIVERY_ID = 1L;
    private final OrderRepository orderRepository;
    private final DeliveryService deliveryService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, DeliveryService deliveryService) {
        this.orderRepository = orderRepository;
        this.deliveryService = deliveryService;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public List<Order> findAllByOpenDateBetweenOrderByOpenDateDesc(LocalDateTime from, LocalDateTime to) {
        return orderRepository.findAllByOpenDateBetweenOrderByOpenDateDesc(from, to);
    }

    @Override
    public void addProductToCart(Order order, OrderContent orderContent) {
        order.addOrderContent(orderContent);
    }

    @Override
    public void setOrderDelivery(Order order, Long deliveryId) {
        Delivery delivery = deliveryService.findById(deliveryId);
        order.setDelivery(delivery);
    }

    @Override
    public BigDecimal calculateSubtotalPrice(Order order) {
        BigDecimal subtotalPrice = new BigDecimal(0.0);
        List<OrderContent> orderContentList = order.getOrderContent();
        for (OrderContent orderContent : orderContentList) {
            subtotalPrice = subtotalPrice
                    .add(orderContent.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(orderContent.getProductQty())));
        }
        return subtotalPrice;
    }

    @Override
    public BigDecimal calculateTotalPrice(Order order) {
        BigDecimal totalPrice = new BigDecimal(0.0);
        totalPrice = totalPrice
                .add(calculateSubtotalPrice(order))
                .add(order.getDelivery().getCost());
        return totalPrice;
    }

    @Override
    public Order placeOrder(Order order) {
        order.setOpenDate(LocalDateTime.now());
        order.setCloseDate(null);
        order.setStatus(Status.OPEN);
        return orderRepository.save(order);
    }

    @Override
    public Order createInitialOrder(User userFromSession) {
        Delivery delivery = deliveryService.findById(DEFAULT_DELIVERY_ID);
        return new Order(userFromSession, delivery);
    }
}
