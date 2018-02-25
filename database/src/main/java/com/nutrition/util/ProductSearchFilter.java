package com.nutrition.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author a.shestovsky
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductSearchFilter {

    private String searchTitle;
    private Long searchCategoryId;
    private List<Long> searchBrandsId;
}
