package com.bookhive.ufcg.bookhive.model;

import jakarta.persistence.Column;

import jakarta.persistence.*;


@Entity
@Table(name = "livro")
public class Book {

    @Id
    private String isbn;

    @Column(name = "rating")
    private float rating;

    public Book(){

    }
    public Book(String isbn, float rating) {
        this.isbn = isbn;
        this.rating = rating;
    }

    public String getisbn() {
        return isbn;
    }

    public float getRating() {
        return rating;
    }

    public void setisbn(String isbn) {
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
