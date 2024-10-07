package com.bookhive.ufcg.bookhive.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class BookDTO {

    private String isbn;

    private float rating;

    public BookDTO(String isbn, float rating) {
        this.isbn = isbn;
        this.rating = rating;
    }

    public String getIsbn() {
        return isbn;
    }

    public float getRating() {
        return rating;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", rating=" + rating +
                '}';
    }
}
