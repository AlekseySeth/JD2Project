package com.nutrition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("language")
public class LanguageController {

    @PostMapping("/language")
    public String selectLanguage(String language, Model model, @RequestHeader("Referer") String referer) {
        model.addAttribute("language", language);
        return "redirect:" + referer;
    }
}
