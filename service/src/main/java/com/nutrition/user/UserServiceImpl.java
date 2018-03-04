package com.nutrition.user;

import com.nutrition.entity.user.Role;
import com.nutrition.entity.user.SystemUser;
import com.nutrition.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(SystemUser systemUser) {
        userRepository.save(systemUser);
    }

    @Override
    public SystemUser findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<SystemUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public SystemUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SystemUser systemUser = userRepository.findByEmail(email);
        if (systemUser == null) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        return new User(email, systemUser.getPassword(), generateAuthorities(systemUser.getRole()));
    }

    private Collection<? extends GrantedAuthority> generateAuthorities(Role role) {
        return Arrays.asList(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public boolean loginSystemUser(UserDetails foundUser, String password) {
        return foundUser.getPassword().equals(password);
    }

    @Override
    public String encryptPassword(String email, String originalPassword) {
        return originalPassword;
    }
}
