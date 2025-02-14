package com.proiect.proiect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clasa pentru HomeController
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/home")
    public String loggedInHome() {
        return "index";
    }

}



