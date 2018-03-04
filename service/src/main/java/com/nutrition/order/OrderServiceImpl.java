package com.nutrition.order;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.user.SystemUser;
import com.nutrition.entity.user.SystemUser;
import com.nutrition.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> findAllByUser(SystemUser systemUser) {
        return orderRepository.findAllByUser(systemUser);
    }

    @Override
    public List<Order> findAllByOpenDateBetweenOrderByOpenDateDesc(LocalDateTime from, LocalDateTime to) {
        return orderRepository.findAllByOpenDateBetweenOrderByOpenDateDesc(from, to);
    }
}
