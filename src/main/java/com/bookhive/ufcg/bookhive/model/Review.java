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
	private User user;

	@ManyToOne
	@JoinColumn(name = "livro_isbn", referencedColumnName = "isbn")
	private Book book;

	@Column(name = "data_inicio")
	private Date startDate;

	@Column(name = "data_fim")
	private Date endDate;

	@Column(name = "avaliacao")
	private Integer rating;

	@Column(name = "conteudo")
	private String content;
    
    public Review() {}
    
    public Review(User user, Book book, Date startDate,  Date endDate, Integer rating, String comments) {
    	this.review_id = UUID.randomUUID().toString();
    	this.user = user;
    	this.book = book;
    	this.startDate = startDate;
    	this.endDate = endDate;
    	this.rating = rating;
    	this.content = comments;
    }

	public String getId() {return review_id;}
	
	public User getUserNameUser() {return user;}
	
	public Book getBookIsbn() {return book;}
	
	public Date getStartDate() {return startDate;}
	
	public Date getEndDate() {return endDate;}
	
	public Integer getRating() {return rating;}
	
	public String getContent() {return content;}
	
	public void setId(String id) {this.review_id = id;}

	public void setStartDate(Date startDate) {this.startDate = startDate;}
	
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	
	public void setRating(Integer rating) {this.rating = rating;}
	
	public void setContent(String comments) {this.content = comments;}

}