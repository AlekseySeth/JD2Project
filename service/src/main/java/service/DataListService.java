package service;

import dao.BrandDao;
import dao.CategoryDao;
import dao.DeliveryDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;
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
        UserDao userDao = UserDao.newInstance();
        return userDao.getAll();
    }

    public List<Product> getAllProducts() {
        ProductDao productDao = ProductDao.newInstance();
        return productDao.getAll();
    }

    public List<Order> getAllOrders() {
        OrderDao orderDao = OrderDao.newInstance();
        return orderDao.getAll();
    }

    public List<Delivery> getAllDeliveries() {
        DeliveryDao deliveryDao = DeliveryDao.newInstance();
        return deliveryDao.getAll();
    }

    public List<Category> getAllCategories() {
        CategoryDao categoryDao = CategoryDao.newInstance();
        return categoryDao.getAll();
    }

    public List<Brand> getAllBrands() {
        BrandDao brandDao = BrandDao.newInstance();
        return brandDao.getAll();
    }

}
