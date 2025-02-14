package com.proiect.proiect.dto;

/**
 * Clasa pentru UserDTO
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */


public class UserDTO {
    private String username;
    private String email;

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
