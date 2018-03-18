package com.nutrition.marketing;

import com.nutrition.dto.PromoDto;
import com.nutrition.entity.marketing.Promotion;

import java.util.List;

public interface PromotionService {

    Promotion findById(Long promoId);

    List<Promotion> findAll();

    void delete(Long promoId);

    void save(PromoDto promoDto);

    void save(Promotion promotion);
}
