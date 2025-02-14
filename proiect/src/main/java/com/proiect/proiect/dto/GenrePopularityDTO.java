package com.proiect.proiect.dto;

/**
 * Clasa pentru GenrePopularityDTO
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

    public class GenrePopularityDTO {
        private String gen;
        private int numarFilme;

        public GenrePopularityDTO(String gen, int numarFilme) {
            this.gen = gen;
            this.numarFilme = numarFilme;
        }



        public String getGen() {
            return gen;
        }

        public int getNumarFilme() {
            return numarFilme;
        }

        public void setGen(String gen) {
            this.gen = gen;
        }

        public void setNumarFilme(int numarFilme) {
            this.numarFilme = numarFilme;
        }
    }

