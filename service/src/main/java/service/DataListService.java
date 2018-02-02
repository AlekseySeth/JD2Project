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

    private static DataListService INSTANCE;

    public static DataListService newInstance() {
        if (INSTANCE == null) {
            synchronized (DataListService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataListService();
                }
            }
        }
        return INSTANCE;
    }

    public List<User> getAllUsers() {
        return UserDao.newInstance().getAll();
    }

    public List<Product> getAllProducts() {
        return ProductDao.newInstance().getAll();
    }

    public List<Order> getAllOrders() {
        return OrderDao.newInstance().getAll();
    }

    public List<Delivery> getAllDeliveries() {
        return DeliveryDao.newInstance().getAll();
    }

    public List<Category> getAllCategories() {
        return CategoryDao.newInstance().getAll();
    }

    public List<Brand> getAllBrands() {
        return BrandDao.newInstance().getAll();
    }

    public List<Promotion> getAllPromotions() {
        return PromotionDao.newInstance().getAllPromotions();
    }
}
