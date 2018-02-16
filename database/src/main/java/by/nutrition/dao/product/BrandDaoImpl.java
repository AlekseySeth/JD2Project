package by.nutrition.dao.product;

import by.nutrition.dao.common.GenericDaoImpl;
import by.nutrition.entity.product.Brand;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class BrandDaoImpl extends GenericDaoImpl<Brand> implements BrandDao {

}
