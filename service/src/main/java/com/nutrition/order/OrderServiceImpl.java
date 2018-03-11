package com.nutrition.order;

import com.nutrition.entity.order.Delivery;
import com.nutrition.entity.order.Order;
import com.nutrition.entity.order.OrderContent;
import com.nutrition.entity.order.Status;
import com.nutrition.entity.product.Product;
import com.nutrition.entity.user.User;
import com.nutrition.product.ProductService;
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
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, DeliveryService deliveryService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.deliveryService = deliveryService;
        this.productService = productService;
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
    public boolean addProductToCart(Order order, OrderContent contentToAdd) {
        Product product = contentToAdd.getProduct();
        Integer qtyToAdd = contentToAdd.getProductQty();
        int qtyInStock = product.getQtyInStock();
        if (qtyInStock >= qtyToAdd) {
            order.addOrderContent(contentToAdd);
            product.setQtyInStock(qtyInStock - qtyToAdd);
//            productService.update(product);
            return true;
        }
        return false;
    }

    @Override
    public void removeProductFromCart(Order order, Long productId, Integer productQty) {
        List<OrderContent> orderContentList = order.getOrderContent();
        OrderContent orderContentToDelete = null;
        for (OrderContent currentOrderContent : orderContentList) {
            if (currentOrderContent.getProduct().getId().equals(productId)) {
                orderContentToDelete = currentOrderContent;
            }
        }
        order.removeOrderContent(orderContentToDelete);
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

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }
}
