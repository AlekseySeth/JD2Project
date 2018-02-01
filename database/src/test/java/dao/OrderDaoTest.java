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
        ContactDetails contactDetails = new ContactDetails("Mobile", "Address");

        User user = new User("FirstName",  "LastName", "Email",
                "Password", contactDetails, LocalDate.now(), Role.CUSTOMER);
        UserDao.newInstance().save(user);

        Delivery delivery = new Delivery("Delivery",  new BigDecimal(2.50));
        DeliveryDao.newInstance().save(delivery);

        Brand brand = new Brand("Brand", "/Brand");
        BrandDao.newInstance().save(brand);

        Category category = new Category("Category", "/Category");
        CategoryDao.newInstance().save(category);

        Product product = new Product("Product", "Description", new BigDecimal(10.0),
                10, category, brand, "image");
        ProductDao.newInstance().save(product);

        Order order = new Order();
        order.setOpenDate(LocalDateTime.now());
        order.setUser(user);
        order.setStatus(Status.OPEN);
        order.setDelivery(delivery);
        order.addProduct(product, 5);
        order.setTotalPrice(new BigDecimal(10.0));
        OrderDao.newInstance().save(order);

        Session session = SESSION_FACTORY.openSession();
        Order result = session.get(Order.class, 1L);
        String firstName = result.getUser().getFirstName();
        String address = result.getUser().getContactDetails().getAddress();
        String deliveryName = order.getDelivery().getName();
        Map<Product, Integer> products = order.getProducts();
        Product inMap = null;
        for (Map.Entry entry : products.entrySet()) {
            inMap = (Product) entry.getKey();
        }
        String brandName = inMap.getBrand().getName();
        String categoryName = inMap.getCategory().getName();
        String description = inMap.getDescription();
        BigDecimal totalPrice = result.getTotalPrice();

        session.close();

        assertEquals("FirstName", firstName);
        assertEquals("Address", address);
        assertEquals("Delivery", deliveryName);
        assertEquals("Brand", brandName);
        assertEquals("Category", categoryName);
        assertEquals("Description", description);
        assertEquals(new BigDecimal(10.0), totalPrice);
    }

    @Test
    public void get() throws Exception {


    }

}
