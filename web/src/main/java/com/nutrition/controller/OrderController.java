package com.nutrition.controller;

import com.nutrition.entity.order.Delivery;
import com.nutrition.entity.order.Order;
import com.nutrition.entity.order.OrderContent;
import com.nutrition.entity.product.Product;
import com.nutrition.entity.user.User;
import com.nutrition.order.DeliveryService;
import com.nutrition.order.OrderService;
import com.nutrition.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * @author a.shestovsky
 */
@Controller
@SessionAttributes(names = {"user", "order", "isPlaced"})
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final DeliveryService deliveryService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService,
                           DeliveryService deliveryService1) {
        this.orderService = orderService;
        this.productService = productService;
        this.deliveryService = deliveryService1;
    }

    @ModelAttribute("user")
    public User getUserFromSession() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @ModelAttribute("order")
    private void initOrder(Model model) {
        model.addAttribute("isPlaced", false);
        model.addAttribute("order", orderService.createInitialOrder(getUserFromSession()));
    }

    @ModelAttribute("allDeliveries")
    public List<Delivery> allDeliveries() {
        return deliveryService.findAll();
    }

    @GetMapping("/cart")
    public String showCartPage(Model model) {
        Map<String, Object> modelMap = model.asMap();
        Order order = (Order) modelMap.get("order");
        model.addAttribute("subtotalPrice", orderService.calculateSubtotalPrice(order));
        order.setTotalPrice(orderService.calculateTotalPrice(order));
        model.addAttribute("order", order);
        return "cart";
    }

    @PostMapping("/cart")
    public String setDelivery(Model model, Long deliveryId) {
        Map<String, Object> modelMap = model.asMap();
        Order order = (Order) modelMap.get("order");
        orderService.setOrderDelivery(order, deliveryId);
        return "redirect:/cart";
    }

    @PostMapping("/product")
    public String addProductToCart(Long productId, int productQty, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(productId);
        OrderContent orderContent = new OrderContent(product, productQty);
        Map<String, Object> modelMap = model.asMap();
        boolean isPlaced = (boolean) modelMap.get("isPlaced");
        if (isPlaced) {
            initOrder(model);
        }
        Order order = (Order) modelMap.get("order");
        redirectAttributes.addAttribute("id", productId);
        if (!orderService.addProductToCart(order, orderContent)) {
            redirectAttributes.addAttribute("notEnoughInStock", true);
            return "redirect:product";
        } else {
            redirectAttributes.addAttribute("notEnoughInStock", false);
        }
        model.addAttribute("order", order);
        return "redirect:product";
    }

    @PostMapping("/remove-order-content")
    public String removeProductFromCart(Long productToRemoveId, int productToRemoveQty, Model model) {
        Map<String, Object> modelMap = model.asMap();
        Order order = (Order) modelMap.get("order");
        orderService.removeProductFromCart(order, productToRemoveId, productToRemoveQty);
        return "redirect:/cart";
    }

    @PostMapping("/order-placed")
    public String placeOrder(Model model) {
        Map<String, Object> modelMap = model.asMap();
        Order order = (Order) modelMap.get("order");
        orderService.placeOrder(order);
        model.addAttribute("isPlaced", true);
        return "redirect:/order-placed";
    }

    @GetMapping("order-placed")
    public String showOrderPlacedPage() {
        return "order-placed";
    }
}
