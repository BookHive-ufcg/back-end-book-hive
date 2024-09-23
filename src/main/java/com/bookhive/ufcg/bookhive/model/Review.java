package com.bookhive.ufcg.bookhive.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "review")
public class Review {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_isbn", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_username", nullable = false)
    private User user;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comments")
    private String comments;

    @Column(name = "review_date")
    private LocalDate reviewDate;
    
    public Review() {
        this.reviewDate = LocalDate.now();
    }

    public Review(Book book, User user, Integer rating, String comments) {
        this.book = book;
        this.user = user;
        this.rating = rating;
        this.comments = comments;
        this.reviewDate = LocalDate.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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