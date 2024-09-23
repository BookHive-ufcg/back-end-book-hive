package com.bookhive.ufcg.bookhive.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class BookDTO {

    private String isbn;
    private float rating;
    private List<ReviewDTO> reviews;

    public BookDTO() {
    }

    public BookDTO(String isbn, float rating, List<ReviewDTO> reviews) {
        this.isbn = isbn;
        this.rating = rating;
        this.reviews = reviews;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
