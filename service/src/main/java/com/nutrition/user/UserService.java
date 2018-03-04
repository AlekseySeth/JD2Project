package com.nutrition.user;

import com.nutrition.entity.user.SystemUser;
import com.nutrition.entity.user.SystemUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(SystemUser systemUser);

    SystemUser findById(Long id);

    List<SystemUser> findAll();

    SystemUser findByEmail(String email);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    boolean loginSystemUser(UserDetails foundUser, String password);

    String encryptPassword(String email, String originalPassword);
}
