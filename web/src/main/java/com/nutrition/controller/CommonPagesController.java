package com.nutrition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonPagesController {

    @GetMapping("*")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/articles")
    public String showArticlesPage() {
        return "articles";
    }

    @GetMapping("/delivery-info")
    public String showDeliveryInfoPage() {
        return "delivery-info";
    }

    @GetMapping("/contact-us")
    public String showContactUsPage() {
        return "contact-us";
    }
}
