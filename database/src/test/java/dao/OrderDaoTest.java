package dao;

import entity.order.Delivery;
import entity.order.Order;
import entity.order.Status;
import entity.product.Brand;
import entity.product.Category;
import entity.product.Product;
import entity.user.ContactDetails;
import entity.user.Role;
import entity.user.User;
import org.hibernate.Session;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author a.shestovsky
 */
public class OrderDaoTest extends BaseTest {
    @Test
    public void save() throws Exception {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, 1L);
        Delivery delivery = session.get(Delivery.class, 1L);
        Product product = session.get(Product.class, 1L);

        Order order = new Order();
        order.setOpenDate(LocalDateTime.now());
        order.setUser(user);
        order.setStatus(Status.OPEN);
        order.setDelivery(delivery);
        order.addProduct(product, 5);
        order.setTotalPrice(new BigDecimal(10.55));

        OrderDao.newInstance().save(order);

        Order result = session.get(Order.class, 2L);

        Product inMap = null;
        for (Map.Entry entry : result.getProducts().entrySet()) {
            inMap = (Product) entry.getKey();
        }

        session.close();

        assertEquals("FirstName", result.getUser().getFirstName());
        assertEquals("Address", result.getUser().getContactDetails().getAddress());
        assertEquals("Delivery", result.getDelivery().getName());
        assertEquals("Brand", inMap.getBrand().getName());
        assertEquals("Category", inMap.getCategory().getName());
        assertEquals("Description", inMap.getDescription());
    }

    @Test
    public void get() throws Exception {
        Order order = OrderDao.newInstance().get(1L);
        Map<Product, Integer> products = order.getProducts();

        Product inMap = null;
        for (Map.Entry entry : products.entrySet()) {
            inMap = (Product) entry.getKey();
        }

        assertEquals("FirstName", order.getUser().getFirstName());
        assertEquals("Address", order.getUser().getContactDetails().getAddress());
        assertEquals("Delivery", order.getDelivery().getName());
        assertEquals("Brand", inMap.getBrand().getName());
        assertEquals("Category", inMap.getCategory().getName());
        assertEquals("Description", inMap.getDescription());

    }
}
