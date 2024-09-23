package com.bookhive.ufcg.bookhive.dto;

import java.sql.Date;
import java.util.List;
import java.time.LocalDate;

public class ReviewDTO {

    private Long id;
    private String bookIsbn;
    private String username;
    private Integer rating;
    private String comments;
    private LocalDate reviewDate;

    public ReviewDTO() {}

    public ReviewDTO(Long id, String bookIsbn, String username, Integer rating, String comments, LocalDate reviewDate) {
        this.id = id;
        this.bookIsbn = bookIsbn;
        this.username = username;
        this.rating = rating;
        this.comments = comments;
        this.reviewDate = reviewDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
