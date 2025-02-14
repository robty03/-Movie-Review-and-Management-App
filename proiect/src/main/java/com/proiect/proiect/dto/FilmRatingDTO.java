package com.proiect.proiect.dto;

/**
 * Clasa pentru FilmRatingDTO
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

public class FilmRatingDTO {
    private String titlu;
    private String gen;
    private double rating;

    public FilmRatingDTO(String titlu, String gen, double rating) {
        this.titlu = titlu;
        this.gen = gen;
        this.rating = rating;
    }

    // Getteri și setteri

    public String getTitlu() {
        return titlu;
    }

    public String getGen() {
        return gen;
    }

    public double getRating() {
        return rating;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
