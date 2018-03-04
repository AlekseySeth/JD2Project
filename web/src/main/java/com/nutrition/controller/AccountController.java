package com.nutrition.controller;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.user.SystemUser;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public SystemUser initUser() {
        return new SystemUser();
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSystemUser(String email, String password, RedirectAttributes systemUser) {
        UserDetails foundUser = userService.loadUserByUsername(email);
        String encryptedPassword = userService.encryptPassword(email, password);
        if (userService.loginSystemUser(foundUser, encryptedPassword)) {
            return "redirect:/my-account";
        } else {
            return "login";
        }
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerCustomer() {

        return "redirect:/my-account";
    }

    @GetMapping("/my-account")
    public String showMyAccountPage(SystemUser systemUser, Model model) {
        List<Order> allOrdersByUser = orderService.findAllByUser(systemUser);
        model.addAttribute("allOrdersByUser", allOrdersByUser);
        return "my-account";
    }
}
