package dao;

import entity.order.Order;
import entity.order.OrderContent;
import entity.product.Product;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author a.shestovsky
 */
public class OrderContentDaoTest {
    @Test
    public void save() throws Exception {
        Session session = TestSuite.sessionFactory.openSession();

        Product product = session.get(Product.class, 1L);
        Order order = session.get(Order.class, 1L);
        OrderContent orderContent = new OrderContent(product, 8, order);

        OrderContentDaoImpl.newInstance().save(orderContent);

        OrderContent result = session.get(OrderContent.class, 2L);

        assertEquals("Product", result.getProduct().getTitle());
        assertEquals(Integer.valueOf(8), result.getProductQty());
    }

    @Test
    public void get() throws Exception {
        OrderContent orderContent = OrderContentDaoImpl.newInstance().findById(1L);

        assertEquals("Product", orderContent.getProduct().getTitle());
        assertEquals(Integer.valueOf(5), orderContent.getProductQty());
    }
}