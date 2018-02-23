package com.nutrition.controller;

import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.product.BrandService;
import com.nutrition.product.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

/**
 * @author a.shestovsky
 */

@Controller
public class ProductSearchController {

    @ModelAttribute("allCategories")
    public List<Category> allCategories(CategoryService categoryService) {
        return categoryService.findAll();
    }

    @ModelAttribute("allBrands")
    public List<Brand> allBrands(BrandService brandService) {
        return brandService.findAll();
    }

    @ModelAttribute("productsOnPage")
    public List<Integer> productsOnPage() {
        return Arrays.asList(3, 5, 10);
    }

    @GetMapping("/products")
    public String showProductsSearchPage() {
        return "products-search";
    }
}
