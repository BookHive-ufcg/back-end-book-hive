package com.bookhive.ufcg.bookhive.dto;

import java.sql.Date;

public class ReviewDTO {
    private String review_id;
    private String username_user;
    private String bookIsbn;
    private Date startDate;
    private Date endDate;
    private Integer rating;  
    private String content;
	
    public ReviewDTO() {}

    public ReviewDTO(String review_id, String username_user, String bookIsbn, Date startDate, Date endDate, Integer rating, String content) {
        this.review_id = review_id;
        this.username_user = username_user;
        this.bookIsbn = bookIsbn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rating = rating;
        this.content = content;
    }
    
    public String getId() {return review_id;}
    
	public String getUsernameUser() {return username_user;}
    
	public String getBookIsbn() {return bookIsbn;}
	
	public Date getStartDate() {return startDate;}
	
	public Date getEndDate() {return endDate;}
	
	public Integer getRating() {return rating;}
	
	public String getContent() {return content;}
    
	public void setId(String id) {this.review_id = id;}
	
	public void setUsernameUser(String username_user) {this.username_user = username_user;}
	
	public void setBookIsbn(String bookIsbn) {this.bookIsbn = bookIsbn;}
	
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	
	public void setRating(Integer rating) {this.rating = rating;}
	
	public void setContent(String content) {this.content = content;}
    
}