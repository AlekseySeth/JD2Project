package com.nutrition.dao.product;

import com.nutrition.dao.common.GenericDaoImpl;
import com.nutrition.entity.product.Category;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

}
