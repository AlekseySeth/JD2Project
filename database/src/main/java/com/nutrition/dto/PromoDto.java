package com.nutrition.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Getter
@Setter
public class PromoDto {

    private String name;
    private boolean active;
    private BigDecimal discount;
    private boolean percentage;
}
