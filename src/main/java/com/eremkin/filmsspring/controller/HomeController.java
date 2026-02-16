package com.eremkin.filmsspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("appName", "Films-Spring");
        return "home";
    }

    @GetMapping("/actors")
    public String actors() {
        return "actors";
    }

    @GetMapping("/genres")
    public String genres() {
        return "genres";
    }

    @GetMapping("/films")
    public String films() {
        return "films";
    }
}
