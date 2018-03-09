package com.nutrition.controller;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.user.Role;
import com.nutrition.entity.user.User;
import com.nutrition.order.OrderService;
import com.nutrition.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerCustomer(User user, Model model, HttpServletRequest request) {
        User registered = userService.registerNewCustomer(user);
        model.addAttribute("user", registered);
        try {
            request.login(user.getEmail(), user.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/my-account";
    }

    @GetMapping("/my-account")
    public String showMyAccountPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        if (user.getRole().equals(Role.ADMIN)) {
            return "admin";
        } else if (user.getRole().equals(Role.MARKETER)) {
            return "marketer";
        } else {
            List<Order> allOrdersByUser = orderService.findAllByUser(user);
            model.addAttribute("allOrdersByUser", allOrdersByUser);
            return "my-account";
        }
    }

    @GetMapping("/admin")
    public String showAdminAccountPage() {
        return "admin";
    }

    @GetMapping("/marketer")
    public String showMarketerAccountPage() {
        return "marketer";
    }
}
