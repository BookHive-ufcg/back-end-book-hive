package com.bookhive.ufcg.bookhive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookhive.ufcg.bookhive.dto.ReviewDTO;
import com.bookhive.ufcg.bookhive.exception.ReviewNotFoundException;
import com.bookhive.ufcg.bookhive.exception.ReviewConflictException;
import com.bookhive.ufcg.bookhive.model.Review;
import com.bookhive.ufcg.bookhive.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    
    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody ReviewDTO reviewDTO) {
        try {
            reviewService.addReview(reviewDTO);
            return new ResponseEntity<>("Resenha criada com sucesso", HttpStatus.CREATED);
        } catch (ReviewConflictException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        try {
            Review review = reviewService.getReviewById(id);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (ReviewNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping
    public ResponseEntity<List<String>> listReviews() {
        List<String> reviews = reviewService.listReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(
            @PathVariable String id,
            @RequestBody ReviewDTO reviewDTO) {
        try {
            reviewService.updateReview(
                    id,
                    reviewDTO.getBookTitle(),
                    reviewDTO.getBookId(),
                    reviewDTO.getStartDate(),
                    reviewDTO.getEndDate(),
                    reviewDTO.getRating(),
                    reviewDTO.getComments()
            );
            return new ResponseEntity<>("Resenha atualizada com sucesso", HttpStatus.OK);
        } catch (ReviewNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable String id) {
        try {
            reviewService.removeReview(id);
            return new ResponseEntity<>("Resenha removida com sucesso", HttpStatus.OK);
        } catch (ReviewNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
