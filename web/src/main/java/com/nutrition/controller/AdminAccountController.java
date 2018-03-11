package com.nutrition.controller;

import com.nutrition.entity.user.User;
import com.nutrition.order.OrderService;
import com.nutrition.product.ProductService;
import com.nutrition.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author a.shestovsky
 */
@Controller
@SessionAttributes(names = {"registeredUser"})
public class AdminAccountController {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public AdminAccountController(ProductService productService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @ModelAttribute("registeredUser")
    public User initRegisteredUser() {
        return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/admin")
    public String showAdminAccountPage() {
        return "admin";
    }

    @GetMapping("/products-list")
    public String showProductListPage(Model model) {
        model.addAttribute("allProducts", productService.findAll());
        return "products-list";
    }

    @GetMapping("/orders-list")
    public String showOrdersListPage(Model model) {
        model.addAttribute("allOrders", orderService.findAll());
        return "orders-list";
    }


    @GetMapping("/users-list")
    public String showUsersListPage(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "users-list";
    }
}
