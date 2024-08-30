package com.bookhive.ufcg.bookhive.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bookhive.ufcg.bookhive.model.Review;

@Repository
public class ReviewRepository {

    private Map<String, Review> reviews;

    public ReviewRepository() {
        this.reviews = new HashMap<>();
    }

    public Collection<Review> getAll() {
        return reviews.values();
    }

   
    public void addReview(Review review) {
        this.reviews.put(review.getId(), review);
    }

    
    public Review getReview(String id) {
        return this.reviews.get(id);
    }

    
    public void removeReview(String id) {
        this.reviews.remove(id);
    }

    
    public void updateReview(String id, String bookTitle, String bookId, LocalDate startDate, LocalDate endDate, Integer rating, String comments) {
        Review review = this.getReview(id);
        if (review != null) {
            review.setBookTitle(bookTitle);
            review.setBookId(bookId);
            review.setStartDate(startDate);
            review.setEndDate(endDate);
            review.setRating(rating);
            review.setComments(comments);
        }
    }

    public boolean hasReview(String id) {
        return this.reviews.containsKey(id);
    }


    public List<String> listReviews() {
        Collection<Review> reviews = this.reviews.values();
        List<String> reviewList = new ArrayList<>();
        for (Review review : reviews) {
            String info = ("Review ID: " + review.getId() + ", Book title: " + review.getBookTitle() + ", Rating: " + review.getRating());
            reviewList.add(info);
        }

        return reviewList;
    }
}

