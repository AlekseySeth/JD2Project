package by.nutrition.dao;

import by.nutrition.entity.marketing.FixedPricePromotion;
import by.nutrition.entity.marketing.PercentageDiscountPromotion;
import by.nutrition.entity.order.Delivery;
import by.nutrition.entity.order.Order;
import by.nutrition.entity.order.OrderContent;
import by.nutrition.entity.order.Status;
import by.nutrition.entity.product.Brand;
import by.nutrition.entity.product.Category;
import by.nutrition.entity.product.Product;
import by.nutrition.entity.user.ContactDetails;
import by.nutrition.entity.user.Role;
import by.nutrition.entity.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author a.shestovsky
 */

public final class TestDataImporter {

    private static TestDataImporter INSTANCE;

    private TestDataImporter() {
    }

    public static TestDataImporter newInstance() {
        if (INSTANCE == null) {
            synchronized (TestDataImporter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TestDataImporter();
                }
            }
        }
        return INSTANCE;
    }

    public void importTestData(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();

        ContactDetails contactDetails = new ContactDetails("Mobile", "Address");
        User user = new User("FirstName",  "LastName", "Email",
                "Password", contactDetails, LocalDate.now(), Role.CUSTOMER);
        Delivery delivery = new Delivery("Delivery",  new BigDecimal(2.50));
        Brand brand = new Brand("Brand", "/Brand");
        Category category = new Category("Category", "/Category");

        FixedPricePromotion fixedPricePromotion = new FixedPricePromotion("FixedPricePromo", false, new BigDecimal(20));
        PercentageDiscountPromotion percentageDiscountPromotion = new PercentageDiscountPromotion("PercentageDiscountPromo", true, 50);

        Product product = new Product("Product", "Description", new BigDecimal(10.55),
                10, category, brand, percentageDiscountPromotion, "image");

        Order order = new Order();

        OrderContent orderContent = new OrderContent(product, 5, order);

        order.setOpenDate(LocalDateTime.now());
        order.setUser(user);
        order.setStatus(Status.OPEN);
        order.setDelivery(delivery);
        order.setTotalPrice(new BigDecimal(10.55));


        session.save(user);
        session.save(delivery);
        session.save(brand);
        session.save(category);
        session.save(fixedPricePromotion);
        session.save(percentageDiscountPromotion);
        session.save(product);
        session.save(order);
        session.save(orderContent);

        session.close();

    }
}
