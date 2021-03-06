package com.nutrition.entity.marketing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "percentage_discount_promos")
@PrimaryKeyJoinColumn(name = "promo_id")
public class PercentageDiscountPromotion extends Promotion {

    @Column(name = "discount_value", nullable = false)
    private int discountValue;

    public PercentageDiscountPromotion(String name, boolean isActive, int discountValue) {
        super(name, isActive);
        this.discountValue = discountValue;
    }
}
