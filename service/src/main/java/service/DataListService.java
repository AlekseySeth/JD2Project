package service;

import by.nutrition.dao.product.BrandDaoImpl;
import by.nutrition.dao.product.CategoryDaoImpl;
import by.nutrition.dao.order.DeliveryDaoImpl;
import by.nutrition.dao.order.OrderDaoImpl;
import by.nutrition.dao.product.ProductDaoImpl;
import by.nutrition.dao.marketing.PromotionDaoImpl;
import by.nutrition.dao.user.UserDaoImpl;
import by.nutrition.entity.marketing.Promotion;
import by.nutrition.entity.order.Delivery;
import by.nutrition.entity.order.Order;
import by.nutrition.entity.product.Brand;
import by.nutrition.entity.product.Category;
import by.nutrition.entity.product.Product;
import by.nutrition.entity.user.User;
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
        return UserDaoImpl.newInstance().findAll();
    }

    public List<Product> getAllProducts() {
        return ProductDaoImpl.newInstance().findAll();
    }

    public List<Order> getAllOrders() {
        return OrderDaoImpl.newInstance().findAll();
    }

    public List<Delivery> getAllDeliveries() {
        return DeliveryDaoImpl.newInstance().findAll();
    }

    public List<Category> getAllCategories() {
        return CategoryDaoImpl.newInstance().findAll();
    }

    public List<Brand> getAllBrands() {
        return BrandDaoImpl.newInstance().findAll();
    }

    public List<Promotion> getAllPromotions() {
        return PromotionDaoImpl.newInstance().findAllPromotions();
    }
}
