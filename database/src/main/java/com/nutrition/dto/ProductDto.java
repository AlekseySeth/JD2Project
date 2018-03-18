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
public class ProductDto {

    private String title;
    private String description;
    private BigDecimal price;
    private int qtyInStock;
    private Long categoryId;
    private Long brandId;
    private Long promotionId;
    private String imageURL;
}
