package com.nutrition.repository.product;

import com.nutrition.repository.common.GenericDaoImpl;
import com.nutrition.entity.product.Brand;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class BrandDaoImpl extends GenericDaoImpl<Brand> implements BrandDao {

}
