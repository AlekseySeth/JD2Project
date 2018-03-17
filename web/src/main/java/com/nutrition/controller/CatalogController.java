package com.nutrition.controller;

import com.nutrition.entity.order.OrderContent;
import com.nutrition.entity.product.Brand;
import com.nutrition.entity.product.Category;
import com.nutrition.entity.product.Product;
import com.nutrition.product.BrandService;
import com.nutrition.product.CategoryService;
import com.nutrition.product.ProductService;
import com.nutrition.util.ProductSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * @author a.shestovsky
 */
@Controller
@SessionAttributes(names = {"allCategories", "productsByFilter", "totalPages"})
public class CatalogController {

    private static final int TWENTY = 20;
    private static final int TEN = 10;
    private static final int FIVE = 5;

    private static final Integer DEFAULT_PAGE_NUMBER = 1;

    private final CategoryService categoryService;
    private final ProductService productService;
    private final BrandService brandService;

    @Autowired
    public CatalogController(CategoryService categoryService, ProductService productService, BrandService brandService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.brandService = brandService;
    }

    @ModelAttribute("allCategories")
    public List<Category> allCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("allBrands")
    public List<Brand> allBrands() {
        return brandService.findAll();
    }

    @ModelAttribute("productsOnPage")
    public List<Integer> productsOnPage() {
        return Arrays.asList(FIVE, TEN, TWENTY);
    }

    @ModelAttribute("orderContent")
    public OrderContent initOrderContent() {
        return new OrderContent();
    }

    @ModelAttribute("productSearchFilter")
    public ProductSearchFilter initProductSearchFilter() {
        return new ProductSearchFilter();
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

    @GetMapping("/product-search/{pageNumber}/filter")
    public String showProductsSearchPage(ProductSearchFilter productSearchFilter, @PathVariable Integer pageNumber, Integer showProductsOnPage, Model model) {
        if (showProductsOnPage == null) {
            showProductsOnPage = FIVE;
        }
        Integer totalPages = productService.countPagesByFilter(productSearchFilter, showProductsOnPage);
        if (totalPages < DEFAULT_PAGE_NUMBER) {
            totalPages = DEFAULT_PAGE_NUMBER;
        }
        List<Product> productsByFilter = productService.findProductsByFilter(productSearchFilter, pageNumber, showProductsOnPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentlyShownProductsOnPage", showProductsOnPage);
        model.addAttribute("productsByFilter", productsByFilter);
        model.addAttribute("currentPage", pageNumber);
        return "product-search";
    }
}
