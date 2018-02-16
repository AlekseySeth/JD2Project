package by.nutrition.dao.product;

import by.nutrition.dao.common.GenericDaoImpl;
import by.nutrition.entity.product.Category;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

}
