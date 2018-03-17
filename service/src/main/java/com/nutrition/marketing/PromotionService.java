package com.nutrition.marketing;

import com.nutrition.entity.marketing.Promotion;

import java.util.List;

public interface PromotionService {

    Promotion findById(Long promoId);

    List<Promotion> findAll();

    void save(Promotion promotion);
}
