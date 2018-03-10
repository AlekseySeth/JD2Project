package com.nutrition.controller;

import com.nutrition.entity.order.OrderContent;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import com.nutrition.product.CategoryService;
import com.nutrition.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * @author a.shestovsky
 */
@Controller
@SessionAttributes(names = {"allCategories"})
public class CatalogController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public CatalogController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @ModelAttribute("allCategories")
    public List<Category> allCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("orderContent")
    public OrderContent initOrderContent() {
        return new OrderContent();
    }

    @GetMapping("/category-list")
    public String showCategoryListPage() {
        return "category-list";
    }

    @GetMapping("/category")
    public String showCategoryListPage(@RequestParam("id") Long categoryId, Model model) {
        List<Product> productsByCategory = productService.findAllByCategory(categoryId);
        Category category = categoryService.findById(categoryId);
        model.addAttribute("category", category);
        model.addAttribute("products", productsByCategory);
        return "category";
    }

    @GetMapping("/product")
    public String showProductDetailsPage(@RequestParam("id") Long productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "product";
    }
}
