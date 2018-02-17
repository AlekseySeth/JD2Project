package com.nutrition.order;

import com.nutrition.entity.order.Delivery;
import com.nutrition.repository.order.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author a.shestovsky
 */

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Delivery findById(Long id) {
        return deliveryRepository.findOne(id);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }
}
