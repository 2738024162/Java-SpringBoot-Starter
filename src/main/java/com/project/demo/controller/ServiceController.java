package com.project.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
    @GetMapping(value = "/menu")
    public String menu() {
        return "redirect:/menu.html";
    }
}
