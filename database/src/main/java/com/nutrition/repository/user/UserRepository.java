package com.nutrition.repository.user;

import com.nutrition.entity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByEmail(String email);

    List<User> findAll();
}
