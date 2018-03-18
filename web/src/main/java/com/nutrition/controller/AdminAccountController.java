package com.nutrition.controller;

import com.nutrition.entity.order.Order;
import com.nutrition.entity.order.Status;
import com.nutrition.entity.product.Product;
import com.nutrition.entity.user.User;
import com.nutrition.order.OrderService;
import com.nutrition.product.BrandService;
import com.nutrition.product.CategoryService;
import com.nutrition.product.ProductService;
import com.nutrition.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

/**
 * @author a.shestovsky
 */
@Controller
@SessionAttributes(names = {"registeredUser", "productToUpdate"})
public class AdminAccountController {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final BrandService brandService;


    @Autowired
    public AdminAccountController(ProductService productService, OrderService orderService,
                                  UserService userService, CategoryService categoryService,
                                  BrandService brandService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @ModelAttribute("registeredUser")
    public User initRegisteredUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @ModelAttribute("allStatuses")
    public Status[] initStatuses() {
        return Status.values();
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

    @PostMapping("/user/id/show")
    public String showUserByIdPageShow(Long userId, RedirectAttributes redirectAttributes) {
        if ("".equals(userId) || userId == null) {
            return "redirect:/users-list";
        }
        redirectAttributes.addAttribute("userId", userId);
        return "redirect:/user/id";
    }

    @GetMapping("/user/id")
    public String showUserByIdPage(Long userId, Model model) {
        User foundUser = userService.findById(userId);
        if (foundUser == null) {
            return "redirect:/users-list";
        }
        model.addAttribute("foundUser", foundUser);
        model.addAttribute("foundUserOrders", orderService.findAllByUser(foundUser));
        return "user";
    }

    @PostMapping("/user/email/show")
    public String showUserByIdPageShow(String userEmail, RedirectAttributes redirectAttributes) {
        if ("".equals(userEmail) || userEmail == null) {
            return "redirect:/users-list";
        }
        redirectAttributes.addAttribute("userEmail", userEmail);
        return "redirect:/user/email";
    }

    @GetMapping("/user/email")
    public String showUserByEmailPage(String userEmail, Model model) {
        User foundUser = userService.findByEmail(userEmail);
        if (foundUser == null) {
            return "redirect:/users-list";
        }
        model.addAttribute("foundUser", foundUser);
        model.addAttribute("foundUserOrders", orderService.findAllByUser(foundUser));
        return "user";
    }

    @PostMapping("/update-product/show")
    public String redirectToUpdateProductPage(Long productId, RedirectAttributes redirectAttributes) {
        if ("".equals(productId) || productId == null) {
            return "redirect:/products-list";
        }
        redirectAttributes.addAttribute("productId", productId);
        return "redirect:/update-product";
    }

    @GetMapping("/update-product")
    public String showProductUpdatePage(Long productId, Model model) {
        Product product = productService.findById(productId);
        if (product == null) {
            return "redirect:/products-list";
        }
        model.addAttribute("productToUpdate", product);
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("allBrands", brandService.findAll());
        return "update-product";
    }

    @PostMapping("/update-product")
    public String updateProduct(Long productId, String title, String description, BigDecimal price, Long promoId,
                                int qtyInStock, String imageURL, Long categoryId, Long brandId) {
        Product product = productService.findById(productId);
        productService.update(product, title, description, price, promoId, qtyInStock, imageURL, categoryId, brandId);
        return "redirect:/update-product?productId=" + productId;
    }

    @PostMapping("/update-order/show")
    public String showUpdateOrderPageShow(Long orderId, RedirectAttributes redirectAttributes) {
        if ("".equals(orderId) || orderId == null) {
            return "redirect:/orders-list";
        }
        redirectAttributes.addAttribute("orderId", orderId);
        return "redirect:/update-order";
    }

    @GetMapping("/update-order")
    public String showUpdateOrderPage(Long orderId, Model model) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return "redirect:/orders-list";
        }
        model.addAttribute("orderToUpdate", order);
        model.addAttribute("orderContentList", order.getOrderContent());
        return "update-order";
    }

    @PostMapping("/update-order")
    public String updateOrder(Long orderId, String status) {
        Order order = orderService.findById(orderId);
        orderService.update(order, status);
        return "redirect:/update-order?orderId=" + orderId;
    }
}
