package com.nutrition.user;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    User findById(Long id);

    List<User> findAll();

    User findByEmail(String email);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User registerNewCustomer(User user);

    Order generateInitialOrder(User user);
}
