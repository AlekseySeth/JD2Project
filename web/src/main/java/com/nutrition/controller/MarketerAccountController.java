package com.nutrition.controller;

import com.nutrition.entity.marketing.Promotion;
import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import com.nutrition.marketing.PromotionService;
import com.nutrition.product.BrandService;
import com.nutrition.product.CategoryService;
import com.nutrition.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * @author a.shestovsky
 */
@Controller
@SessionAttributes(names = {"registeredUser"})
public class MarketerAccountController {

    private final PromotionService promotionService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @Autowired
    public MarketerAccountController(PromotionService promotionService, ProductService productService, CategoryService categoryService, BrandService brandService) {
        this.promotionService = promotionService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @ModelAttribute("allPromotions")
    public List<Promotion> initPromotions() {
        return promotionService.findAll();
    }

    @ModelAttribute("allCategories")
    public List<Category> initCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("allBrands")
    public List<Brand> initBrands() {
        return brandService.findAll();
    }

    @ModelAttribute("productToAdd")
    public Product initProductToAdd() {
        return new Product();
    }

    @GetMapping("/marketer")
    public String showMarketerAccountPage() {
        return "marketer";
    }

    @GetMapping("/add-new-product")
    public String showAddProductPage() {
        return "add-new-product";
    }

    @PostMapping("/add-new-product")
    public String addProduct(Product product) {

        return "redirect:/products-list";
    }

    @GetMapping("/promotions-list")
    public String showPromotionsListPage() {
        return "promotions-list";
    }

    @PostMapping("/add-promotion")
    public String addPromotion() {
        return "redirect:/promotions-list";
    }

}
