package com.proiect.proiect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Clasa pentru LoginController
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */


@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Numele de utilizator sau parola sunt incorecte.");
        }
        return "login";
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
