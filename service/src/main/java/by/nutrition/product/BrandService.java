package by.nutrition.product;

import by.nutrition.entity.product.Brand;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface BrandService {

    Brand findById(Long id);

    List<Brand> findAll();
}
