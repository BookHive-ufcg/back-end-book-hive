package com.bookhive.ufcg.bookhive.model;

import java.util.UUID;
import jakarta.persistence.*;
import java.sql.Date;
 
@Entity
@Table(name = "reviews")
public class Review {
	
	@Id
    private String review_id;

	@ManyToOne
	@JoinColumn(name = "usuario_username", referencedColumnName = "username")
    private String username_user;
    
	@ManyToOne
	@JoinColumn(name = "livro_isbn", referencedColumnName = "isbn")
    private String bookIsbn;

    @Column(name = "data_inicio")
    private Date startDate;
    
    @Column(name = "data_fim")
    private Date endDate;

    @Column(name = "avaliacao")
    private Integer rating;

    @Column(name = "conteudo")
    private String content;
    
    public Review() {}
    
    public Review(String username_user, String bookIsbn, Date startDate,  Date endDate, Integer rating, String comments) {
    	this.review_id = UUID.randomUUID().toString();
    	this.username_user = username_user;
    	this.bookIsbn = bookIsbn;
    	this.startDate = startDate;
    	this.endDate = endDate;
    	this.rating = rating;
    	this.content = comments;
    }

	public String getId() {return review_id;}
	
	public String getUserNameUser() {return username_user;}
	
	public String getBookIsbn() {return bookIsbn;}
	
	public Date getStartDate() {return startDate;}
	
	public Date getEndDate() {return endDate;}
	
	public Integer getRating() {return rating;}
	
	public String getContent() {return content;}
	
	public void setId(String id) {this.review_id = id;}

	public void setUserNameUser(String username_user) {this.username_user = username_user;}

	public void setBookIsbn(String bookId) {this.bookIsbn = bookId;}

	public void setStartDate(Date startDate) {this.startDate = startDate;}
	
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	
	public void setRating(Integer rating) {this.rating = rating;}
	
	public void setContent(String comments) {this.content = comments;}

}