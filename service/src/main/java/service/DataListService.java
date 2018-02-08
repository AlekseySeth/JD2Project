package service;

import dao.BrandDao;
import dao.CategoryDao;
import dao.DeliveryDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.PromotionDao;
import dao.UserDao;
import entity.marketing.Promotion;
import entity.order.Delivery;
import entity.order.Order;
import entity.product.Brand;
import entity.product.Category;
import entity.product.Product;
import entity.user.User;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author a.shestovsky
 */
@NoArgsConstructor
public class DataListService {

    private static DataListService instance;

    public static DataListService newInstance() {
        if (instance == null) {
            synchronized (DataListService.class) {
                if (instance == null) {
                    instance = new DataListService();
                }
            }
        }
        return instance;
    }

    public List<User> getAllUsers() {
        return UserDao.newInstance().findAll();
    }

    public List<Product> getAllProducts() {
        return ProductDao.newInstance().findAll();
    }

    public List<Order> getAllOrders() {
        return OrderDao.newInstance().findAll();
    }

    public List<Delivery> getAllDeliveries() {
        return DeliveryDao.newInstance().findAll();
    }

    public List<Category> getAllCategories() {
        return CategoryDao.newInstance().findAll();
    }

    public List<Brand> getAllBrands() {
        return BrandDao.newInstance().findAll();
    }

    public List<Promotion> getAllPromotions() {
        return PromotionDao.newInstance().findAllPromotions();
    }
}
