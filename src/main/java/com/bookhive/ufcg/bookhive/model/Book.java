package com.bookhive.ufcg.bookhive.model;

import jakarta.persistence.Column;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "livro")
public class Book {

    @Id
    private String isbn;

    @Column(name = "rating")
    private float rating;
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

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
    
    public List<Review> getReviews() {
        return reviews;
    }
    
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", rating=" + rating +
                '}';
    }
}
