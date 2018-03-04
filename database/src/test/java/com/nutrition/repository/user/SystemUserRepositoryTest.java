package com.nutrition.repository.user;

import com.nutrition.config.PersistenceTestConfig;
import com.nutrition.entity.user.SystemUser;
import com.nutrition.entity.user.SystemUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceTestConfig.class)
@Transactional
public class SystemUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmail() throws Exception {
        SystemUser result = userRepository.findByEmail("Admin");
        assertThat(result.getFirstName(), equalTo("Admin"));
    }

}