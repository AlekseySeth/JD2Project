package com.nutrition.repository.user;

import com.nutrition.entity.user.SystemUser;
import com.nutrition.entity.user.SystemUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface UserRepository extends PagingAndSortingRepository<SystemUser, Long> {

    SystemUser findByEmail(String email);

    List<SystemUser> findAll();
}
