package service;

import dao.BrandDaoImpl;
import dao.CategoryDaoImpl;
import dao.DeliveryDaoImpl;
import dao.OrderDaoImpl;
import dao.ProductDaoImpl;
import dao.PromotionDaoImpl;
import dao.UserDaoImpl;
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
