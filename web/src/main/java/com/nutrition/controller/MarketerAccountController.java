package com.nutrition.controller;

import com.nutrition.dto.ProductDto;
import com.nutrition.dto.PromoDto;
import com.nutrition.entity.marketing.FixedPricePromotion;
import com.nutrition.entity.marketing.PercentageDiscountPromotion;
import com.nutrition.entity.marketing.Promotion;
import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.marketing.PromotionService;
import com.nutrition.product.BrandService;
import com.nutrition.product.CategoryService;
import com.nutrition.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ProductDto initProductToAdd() {
        return new ProductDto();
    }

    @ModelAttribute("promoToAdd")
    public PromoDto initPromoToAdd() {
        return new PromoDto();
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
    public String addProduct(ProductDto productDto) {
        productService.save(productDto);
        return "redirect:/products-list";
    }

    @GetMapping("/promotions-list")
    public String showPromotionsListPage() {
        return "promotions-list";
    }

    @GetMapping("/add-promotion")
    public String showAddPromotionPage() {
        return "add-promotion";
    }

    @PostMapping("/delete-promotion")
    public String deletePromotion(Long promoId) {
        promotionService.delete(promoId);
        return "redirect:/promotions-list";
    }

    @PostMapping("/add-promotion")
    public String addPromotion(PromoDto promoDto) {
        promotionService.save(promoDto);
        return "redirect:/promotions-list";
    }

    @PostMapping("/update-promotion/show")
    public String showUpdatePromotionPageShow(Long promoId, RedirectAttributes redirectAttributes) {
        if ("".equals(promoId) || promoId == null) {
            return "redirect:/promotions-list";
        }
        redirectAttributes.addAttribute("promoId", promoId);
        return "redirect:/update-promotion";
    }

    @GetMapping("/update-promotion")
    public String showUpdatePromotionPage(Long promoId, Model model) {
        Promotion promotion = promotionService.findById(promoId);
        if (promoId == null) {
            return "redirect:/promotions-list";
        }
        if (promotion instanceof PercentageDiscountPromotion) {
            model.addAttribute("percentage", ((PercentageDiscountPromotion) promotion).getDiscountValue());
        }
        if (promotion instanceof FixedPricePromotion) {
            model.addAttribute("fixedPrice", ((FixedPricePromotion) promotion).getFixedPrice());
        }
        model.addAttribute("promoToUpdate", promotion);
        return "update-promotion";
    }

    @PostMapping("/update-promotion")
    public String updatePromotion(boolean active, Long promoId) {
        Promotion promotion = promotionService.findById(promoId);
        promotion.setActive(active);
        promotionService.save(promotion);
        return "redirect:/promotions-list";
    }

}
