package com.bookhive.ufcg.bookhive.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookhive.ufcg.bookhive.dto.UserDTO;
import com.bookhive.ufcg.bookhive.exception.*;
import com.bookhive.ufcg.bookhive.model.Book;
import com.bookhive.ufcg.bookhive.model.User;
import com.bookhive.ufcg.bookhive.repository.BookRepository;
import com.bookhive.ufcg.bookhive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookhive.ufcg.bookhive.dto.ReviewDTO;
import com.bookhive.ufcg.bookhive.model.Review;
import com.bookhive.ufcg.bookhive.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;
 
    public void addReview(ReviewDTO reviewDTO) throws ReviewConflictException,UserNotFoundException, BookNotFoundException {

    	if(this.reviewRepository.existsById(reviewDTO.getId())) {
    		throw new ReviewConflictException("A resenha já existe");
    	}

        User user = userRepository.findById(reviewDTO.getUsernameUser())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        Book book = bookRepository.findById(reviewDTO.getBookIsbn())
                .orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));

    	Review review = new Review(
                user,
                book,
                reviewDTO.getStartDate(),
                reviewDTO.getEndDate(),
                reviewDTO.getRating(),
                reviewDTO.getContent());
    	
    	this.reviewRepository.save(review);
    }
    
    public void updateReview(String id, String username_user, String bookIsbn, Date startDate, Date endDate, int rating, String content) throws ReviewNotFoundException,UserNotFoundException, BookNotFoundException {
    	Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review: " + id + " não encontrado."));

        User user = userRepository.findById(username_user)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        Book book = bookRepository.findById(bookIsbn)
                .orElseThrow(() -> new BookNotFoundException("Livro não encontrado"));

        review.setStartDate(startDate);
        review.setEndDate(endDate);
        review.setRating(rating);
        review.setContent(content);
        
        reviewRepository.save(review);
    }
    
    public void removeReview(String id) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review: " + id + " não encontrado."));
        reviewRepository.delete(review);
    }
    
    public Review getReviewById(String id) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review: " + id + " não encontrado."));
        return review;
    }
    
    public List<Review> listReviews() {
        return new ArrayList<>(reviewRepository.findAll());
    }
     
}
