package com.proiect.proiect.dto;

/**
 * Clasa pentru RecenzieDTO
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

public class RecenzieDTO {
    private String text;
    private int rating;

    public RecenzieDTO(String text, int rating) {
        this.text = text;
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }
}
