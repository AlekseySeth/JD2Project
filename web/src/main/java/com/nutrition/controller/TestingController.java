package com.nutrition.controller;

import com.nutrition.entity.product.Product;
import com.nutrition.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestingController {

    private final ProductService productService;

    @Autowired
    public TestingController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("testing")
    public String testing(int pageNumber, int qtyOnPage, Model model) {
        Page<Product> products = productService.findAll(pageNumber, qtyOnPage);
        model.addAttribute("testProducts", products);
        return "testing";
    }
}
