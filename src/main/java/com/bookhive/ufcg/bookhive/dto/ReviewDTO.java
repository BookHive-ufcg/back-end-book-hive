package com.bookhive.ufcg.bookhive.dto;

import java.time.LocalDate;

public class ReviewDTO {
    private String id;
    private String bookTitle;
    private String bookId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer rating;
    private String comments;
	
    public ReviewDTO() {}

    public ReviewDTO(String id, String bookTitle, String bookId, LocalDate startDate, LocalDate endDate, Integer rating, String comments) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rating = rating;
        this.comments = comments;
    }
    
    public String getId() {
		return id;
	}
    
	public void setId(String id) {
		this.id = id;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}
	
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	public String getBookId() {
		return bookId;
	}
	
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

    
}
