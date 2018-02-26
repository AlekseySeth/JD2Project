package com.nutrition.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * @author a.shestovsky
 */

@Controller
@SessionAttributes("products")
public class ProductSearchController {

    private static final int TEN = 10;
    private static final int FIVE = 5;
    private static final int THREE = 3;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @Autowired
    public ProductSearchController(ProductService productService, CategoryService categoryService,
                                   BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
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
        return Arrays.asList(TEN, FIVE, THREE);
    }

    @ModelAttribute("productSearchFilter")
    public ProductSearchFilter productSearchFilter() {
        return new ProductSearchFilter();
    }

    @ModelAttribute("products")
    public List<Product> initProducts() {
        return productService
                .findByTitleCategoryBrandsViaId(null, null, null, 1, 10);
    }

    @GetMapping("/product-search")
    public String showProductsSearchPage() {
        return "product-search";
    }

    @PostMapping("/product-search")
    public String searchProductsByFilter(ProductSearchFilter productSearchFilter, int pageNumber, int showProductsOnPage, Model model) {
        String searchTitle = productSearchFilter.getSearchTitle();
        Long searchCategoryId = productSearchFilter.getSearchCategoryId();
        List<Long> searchBrandsId = productSearchFilter.getSearchBrandsId();

        List<Product> products = productService
                .findByTitleCategoryBrandsViaId(searchTitle, searchCategoryId, searchBrandsId, pageNumber, showProductsOnPage);

        model.addAttribute("products", products);

        return "product-search";
    }
}
