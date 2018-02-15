package dao;

import dao.common.GenericDao;
import entity.product.Product;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface ProductDao extends GenericDao<Product> {

    List<Product> searchProducts(Long categoryId, String title, List<Long> brandsId, int limit, int offset);
}
