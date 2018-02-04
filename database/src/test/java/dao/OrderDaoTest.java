package dao;

import entity.order.Delivery;
import entity.order.Order;
import entity.order.OrderContent;
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
import java.util.List;
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
        OrderContent orderContentToSave = new OrderContent(product, 3, order);

        order.setOpenDate(LocalDateTime.now());
        order.setUser(user);
        order.setStatus(Status.OPEN);
        order.setDelivery(delivery);

        order.setTotalPrice(new BigDecimal(10.55));

        OrderDao.newInstance().save(order);
        session.save(orderContentToSave);

        Order result = session.get(Order.class, 2L);
        List<OrderContent> orderContent = result.getOrderContent();

        session.close();

        assertEquals("FirstName", result.getUser().getFirstName());
        assertEquals("Address", result.getUser().getContactDetails().getAddress());
        assertEquals("Delivery", result.getDelivery().getName());
        assertEquals("Brand", orderContent.get(0).getProduct().getBrand().getName());
        assertEquals("Category", orderContent.get(0).getProduct().getCategory().getName());
        assertEquals("Description", orderContent.get(0).getProduct().getDescription());
        assertEquals(Integer.valueOf(3), orderContent.get(0).getProductQty());
    }

    @Test
    public void get() throws Exception {
        Order order = OrderDao.newInstance().get(1L);
        List<OrderContent> orderContent = order.getOrderContent();

        assertEquals("FirstName", order.getUser().getFirstName());
        assertEquals("Address", order.getUser().getContactDetails().getAddress());
        assertEquals("Delivery", order.getDelivery().getName());
        assertEquals("Brand", orderContent.get(0).getProduct().getBrand().getName());
        assertEquals("Category", orderContent.get(0).getProduct().getCategory().getName());
        assertEquals("Description", orderContent.get(0).getProduct().getDescription());
        assertEquals(Integer.valueOf(5), orderContent.get(0).getProductQty());

    }
}
