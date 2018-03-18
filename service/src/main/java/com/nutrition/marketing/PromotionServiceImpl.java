package com.nutrition.marketing;

import com.nutrition.dto.PromoDto;
import com.nutrition.entity.marketing.FixedPricePromotion;
import com.nutrition.entity.marketing.PercentageDiscountPromotion;
import com.nutrition.entity.marketing.Promotion;
import com.nutrition.entity.product.Product;
import com.nutrition.product.ProductService;
import com.nutrition.repository.marketing.FixedPricePromotionRepository;
import com.nutrition.repository.marketing.PercentageDiscountPromotionRepository;
import com.nutrition.repository.marketing.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PercentageDiscountPromotionRepository percentageDiscountPromotionRepository;
    private final FixedPricePromotionRepository fixedPricePromotionRepository;
    private final ProductService productService;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository,
                                PercentageDiscountPromotionRepository percentageDiscountPromotionRepository,
                                FixedPricePromotionRepository fixedPricePromotionRepository, ProductService productService) {
        this.promotionRepository = promotionRepository;
        this.percentageDiscountPromotionRepository = percentageDiscountPromotionRepository;
        this.fixedPricePromotionRepository = fixedPricePromotionRepository;
        this.productService = productService;
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
    public void delete(Long promoId) {
        promotionRepository.delete(promoId);
    }

    @Override
    public void save(PromoDto promoDto) {
        Product product = productService.findById(promoDto.getPromotedProductId());
        if (promoDto.isPercentage()) {
            PercentageDiscountPromotion percentageDiscountPromotion = new PercentageDiscountPromotion();
            percentageDiscountPromotion.setName(promoDto.getName());
            percentageDiscountPromotion.setPromotedProducts(Arrays.asList(product));
            percentageDiscountPromotion.setActive(promoDto.isActive());
            percentageDiscountPromotion.setDiscountValue(promoDto.getDiscount().intValue());
            percentageDiscountPromotionRepository.save(percentageDiscountPromotion);
        } else {
            FixedPricePromotion fixedPricePromotion = new FixedPricePromotion();
            fixedPricePromotion.setName(promoDto.getName());
            fixedPricePromotion.setPromotedProducts(Arrays.asList(product));
            fixedPricePromotion.setActive(promoDto.isActive());
            fixedPricePromotion.setFixedPrice(promoDto.getDiscount());
            fixedPricePromotionRepository.save((FixedPricePromotion) fixedPricePromotion);
        }
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
