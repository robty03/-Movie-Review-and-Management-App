package com.proiect.proiect.controller;

import com.proiect.proiect.model.Utilizator;
import com.proiect.proiect.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Clasa pentru SignupController
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Controller
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) {
        try {
            if (userService.existsByUsername(username) || userService.existsByEmail(email)) {
                throw new IllegalArgumentException("Există deja un cont cu acest nume de utilizator sau email.");
            }
            userService.registerUser(new Utilizator(username, password, email));
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("signupError", e.getMessage());
            return "signup";
        }
    }


}
