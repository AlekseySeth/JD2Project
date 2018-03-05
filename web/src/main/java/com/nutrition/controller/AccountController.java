package com.nutrition.controller;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.user.Role;
import com.nutrition.entity.user.User;
import com.nutrition.order.OrderService;
import com.nutrition.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author a.shestovsky
 */
@Controller
@SessionAttributes(names = {"user"})
public class AccountController {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public AccountController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User initUser() {
        return new User();
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

//    @PostMapping("/login")
//    public String loginSystemUser(String email, String password, RedirectAttributes user) {
//
//        return "redirect:/my-account";
//    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerCustomer(User user, Model model) {
        user.setRole(Role.CUSTOMER);
        user.setRegistrationDate(LocalDate.now());
        User registered = userService.registerNewCustomer(user);
        model.addAttribute("user", registered);
        return "redirect:/my-account";
    }

    @GetMapping("/my-account")
    public String showMyAccountPage(User user, Model model) {
        List<Order> allOrdersByUser = orderService.findAllByUser(user);
        model.addAttribute("allOrdersByUser", allOrdersByUser);
        return "my-account";
    }
}
