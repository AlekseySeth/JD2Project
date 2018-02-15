package dao;

import dao.common.GenericDaoImpl;
import entity.product.Category;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> {

}
