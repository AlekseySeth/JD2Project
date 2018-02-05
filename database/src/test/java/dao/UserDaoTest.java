package dao;

import entity.user.ContactDetails;
import entity.user.Role;
import entity.user.User;
import org.hibernate.Session;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * @author a.shestovsky
 */
public class UserDaoTest {

    @Test
    public void save() throws Exception {

        ContactDetails contactDetails = new ContactDetails("Mobile_2", "Address_2");
        User user = new User("FirstName_2",  "LastName_2", "Email_2",
                "Password_2", contactDetails, LocalDate.now(), Role.CUSTOMER);

        UserDao.newInstance().save(user);

        Session session = TestSuite.sessionFactory.openSession();
        User result = session.get(User.class, 2L);

        assertEquals("FirstName_2", result.getFirstName());
        assertEquals("Mobile_2", result.getContactDetails().getMobile());
    }

    @Test
    public void get() throws Exception {
        User user = UserDao.newInstance().get(1L);
        assertEquals("FirstName", user.getFirstName());
        assertEquals("Mobile", user.getContactDetails().getMobile());
    }
}
