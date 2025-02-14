package com.proiect.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Clasa pentru PasswordGenerator
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Parolele brute
        String adminPassword = "parola_admin";
        String userPassword = "parola_user";

        // Codificare parole
        String encodedAdminPassword = passwordEncoder.encode(adminPassword);
        String encodedUserPassword = passwordEncoder.encode(userPassword);

        // Afișare parole codificate
        System.out.println("Parola codificată pentru admin: " + encodedAdminPassword);
        System.out.println("Parola codificată pentru user: " + encodedUserPassword);
    }
}
