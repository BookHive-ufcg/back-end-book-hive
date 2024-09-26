package com.bookhive.ufcg.bookhive.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookhive.ufcg.bookhive.dto.ReviewDTO;
import com.bookhive.ufcg.bookhive.exception.ReviewNotFoundException;
import com.bookhive.ufcg.bookhive.exception.ReviewConflictException;
import com.bookhive.ufcg.bookhive.model.Review;
import com.bookhive.ufcg.bookhive.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
 
    public void addReview(ReviewDTO reviewDTO) throws ReviewConflictException {
    	if(this.reviewRepository.existsById(reviewDTO.getId())) {
    		throw new ReviewConflictException("A resenha já existe");
    	}
        
    	Review review = new Review(
                reviewDTO.getUsernameUser(),
                reviewDTO.getBookIsbn(),
                reviewDTO.getStartDate(),
                reviewDTO.getEndDate(),
                reviewDTO.getRating(),
                reviewDTO.getContent());
    	
    	this.reviewRepository.save(review);
    }
    
    public void updateReview(String id, String username_user, String bookIsbn, Date startDate, Date endDate, int rating, String content) throws ReviewNotFoundException {
    	Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review: " + id + " não encontrado."));
        
        review.setUserNameUser(username_user);
        review.setBookIsbn(bookIsbn);
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
