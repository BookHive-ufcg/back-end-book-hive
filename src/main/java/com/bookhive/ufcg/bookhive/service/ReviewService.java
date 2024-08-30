package com.bookhive.ufcg.bookhive.service;

import java.time.LocalDate;
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
        if (this.reviewRepository.hasReview(reviewDTO.getId())) {
            throw new ReviewConflictException("A resenha já existe");
        }
        Review review = new Review(
                reviewDTO.getBookTitle(),
                reviewDTO.getBookId(),
                reviewDTO.getStartDate(),
                reviewDTO.getEndDate(),
                reviewDTO.getRating(),
                reviewDTO.getComments());

        this.reviewRepository.addReview(review);
    }

 
    public boolean verifyReview(String id) {
        return this.reviewRepository.hasReview(id);
    }


    public void updateReview(String id, String bookTitle, String bookId, LocalDate startDate, LocalDate endDate, Integer rating, String comments) throws ReviewNotFoundException {
        if (!this.reviewRepository.hasReview(id)) {
            throw new ReviewNotFoundException("Resenha com o ID: " + id + " não encontrada");
        }
        this.reviewRepository.updateReview(id, bookTitle, bookId, startDate, endDate, rating, comments);
    }

  
    public void removeReview(String id) throws ReviewNotFoundException {
        if (!this.reviewRepository.hasReview(id)) {
            throw new ReviewNotFoundException("Resenha com o ID: " + id + " não encontrada");
        }
        this.reviewRepository.removeReview(id);
    }

   
    public Review getReviewById(String id) throws ReviewNotFoundException {
        Review review = this.reviewRepository.getReview(id);
        if (review == null) throw new ReviewNotFoundException("Resenha com o ID: " + id + " não encontrada");

        return review;
    }

 
    public List<String> listReviews() {
        return new ArrayList<>(reviewRepository.listReviews());
    }
}
