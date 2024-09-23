package com.bookhive.ufcg.bookhive.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookhive.ufcg.bookhive.dto.ReviewDTO;
import com.bookhive.ufcg.bookhive.exception.ReviewNotFoundException;
import com.bookhive.ufcg.bookhive.exception.ReviewConflictException;
import com.bookhive.ufcg.bookhive.model.Book;
import com.bookhive.ufcg.bookhive.model.Review;
import com.bookhive.ufcg.bookhive.model.User;
import com.bookhive.ufcg.bookhive.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
    private ReviewRepository reviewRepository;

	private ReviewDTO convertToReviewDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getBook().getisbn(),
                review.getUser().getUsername(),
                review.getRating(),
                review.getComments(),
                review.getReviewDate()
        );
    }

	 public List<ReviewDTO> getReviewsByBookIsbn(String isbn) {
	        List<Review> reviews = reviewRepository.findByBookIsbn(isbn);
	        return reviews.stream()
	                .map(this::convertToReviewDTO)
	                .collect(Collectors.toList());
	 }
	 
	  public List<ReviewDTO> getReviewsByUserUsername(String username) {
	        List<Review> reviews = reviewRepository.findByUserUsername(username);
	        return reviews.stream()
	                .map(this::convertToReviewDTO)
	                .collect(Collectors.toList());
	  }
	  
	  public ReviewDTO createReview(ReviewDTO reviewDTO, Book book, User user) {
	        Review review = new Review(book, user, reviewDTO.getRating(), reviewDTO.getComments());
	        Review savedReview = reviewRepository.save(review);
	        return convertToReviewDTO(savedReview);
	  }
	  
	  public boolean deleteReview(Long id) {
	        if (reviewRepository.existsById(id)) {
	            reviewRepository.deleteById(id);
	            return true;
	        }
	        return false;
	  }
}
