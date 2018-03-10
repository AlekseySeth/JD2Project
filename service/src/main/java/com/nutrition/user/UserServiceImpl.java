package com.nutrition.user;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.user.ContactDetails;
import com.nutrition.entity.user.Role;
import com.nutrition.entity.user.User;
import com.nutrition.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        return user;
    }

    @Override
    public User registerNewCustomer(User user) {
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(Role.CUSTOMER);
        user.setRegistrationDate(LocalDate.now());
        return userRepository.save(user);
    }

    @Override
    public Order generateInitialOrder(User user) {

        return null;
    }

    @Override
    public void updateProfile(User user, String firstName, String lastName, String phone, String address) {
        if (firstName.length() > 0) {
            user.setFirstName(firstName);
        }
        user.setLastName(lastName);
        if (phone.length() > 0) {
            ContactDetails contactDetails = user.getContactDetails();
            contactDetails.setMobile(phone);
            user.setContactDetails(contactDetails);
        }
        if (address.length() > 0) {
            ContactDetails contactDetails = user.getContactDetails();
            contactDetails.setAddress(address);
            user.setContactDetails(contactDetails);
        }
        userRepository.save(user);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
