package com.bookhive.ufcg.bookhive.model;

import jakarta.persistence.Column;

import jakarta.persistence.*;


@Entity
@Table(name = "livro")
public class Book {

    @Id
    private String ibsn;

    @Column(name = "rating")
    private float rating;

    public Book(){

    }
    public Book(String ibsn, float rating) {
        this.ibsn = ibsn;
        this.rating = rating;
    }

    public String getIbsn() {
        return ibsn;
    }

    public float getRating() {
        return rating;
    }

    public void setIbsn(String ibsn) {
        this.ibsn = ibsn;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ibsn='" + ibsn + '\'' +
                ", rating=" + rating +
                '}';
    }
}
