package com.nutrition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author a.shestovsky
 */
@Controller
@SessionAttributes(names = {"registeredUser"})
public class MarketerAccountController {

    @GetMapping("/marketer")
    public String showMarketerAccountPage() {
        return "marketer";
    }
}
