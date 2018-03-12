package com.nutrition.marketing;

import com.nutrition.entity.marketing.Promotion;

public interface PromotionService {

    Promotion findById(Long promoId);

    Iterable<Promotion> findAll();

    void save(Promotion promotion);


}
