package dao;

import org.junit.Test;

import static dao.ATest.sessionFactory;

/**
 * @author a.shestovsky
 */
public class XTest {

    @Test
    public void finish() {
        sessionFactory.close();
    }
}
