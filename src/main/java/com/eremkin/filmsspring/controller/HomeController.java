package com.eremkin.filmsspring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        model.addAttribute("appName", "Films-Spring");
        model.addAttribute("roles", authentication.getAuthorities());
        return "home";
    }
}
