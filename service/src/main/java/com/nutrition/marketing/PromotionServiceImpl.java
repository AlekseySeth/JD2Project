package com.nutrition.marketing;

import com.nutrition.entity.marketing.FixedPricePromotion;
import com.nutrition.entity.marketing.PercentageDiscountPromotion;
import com.nutrition.entity.marketing.Promotion;
import com.nutrition.repository.marketing.FixedPricePromotionRepository;
import com.nutrition.repository.marketing.PercentageDiscountPromotionRepository;
import com.nutrition.repository.marketing.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PercentageDiscountPromotionRepository percentageDiscountPromotionRepository;
    private final FixedPricePromotionRepository fixedPricePromotionRepository;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository,
                                PercentageDiscountPromotionRepository percentageDiscountPromotionRepository,
                                FixedPricePromotionRepository fixedPricePromotionRepository) {
        this.promotionRepository = promotionRepository;
        this.percentageDiscountPromotionRepository = percentageDiscountPromotionRepository;
        this.fixedPricePromotionRepository = fixedPricePromotionRepository;
    }

    @Override
    public Promotion findById(Long promoId) {
        return promotionRepository.findOne(promoId);
    }

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public void save(Promotion promotion) {
        if (promotion instanceof PercentageDiscountPromotion) {
            percentageDiscountPromotionRepository.save((PercentageDiscountPromotion) promotion);
        } else if (promotion instanceof FixedPricePromotion) {
            fixedPricePromotionRepository.save((FixedPricePromotion) promotion);
        }
    }
}
