package com.bookhive.ufcg.bookhive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookhive.ufcg.bookhive.dto.ReviewDTO;
import com.bookhive.ufcg.bookhive.exception.BookNotFoundException;
import com.bookhive.ufcg.bookhive.exception.ReviewConflictException;
import com.bookhive.ufcg.bookhive.exception.ReviewNotFoundException;
import com.bookhive.ufcg.bookhive.exception.UserNotFoundException;
import com.bookhive.ufcg.bookhive.model.Review;
import com.bookhive.ufcg.bookhive.service.ReviewService;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    
    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody ReviewDTO reviewDTO) {
        try {
            try {
                reviewService.addReview(reviewDTO);
            } catch (UserNotFoundException | BookNotFoundException e) {
                throw new RuntimeException(e);
            }
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

    
    @RequestMapping(value = "reviews", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> listReviews() {
        List<Review> reviews = reviewService.listReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(
            @PathVariable String id,
            @RequestBody ReviewDTO reviewDTO) {
        try {
            try {
                reviewService.updateReview(
                        id,
                        reviewDTO.getUsernameUser(),
                        reviewDTO.getBookIsbn(),
                        reviewDTO.getStartDate(),
                        reviewDTO.getEndDate(),
                        reviewDTO.getRating(),
                        reviewDTO.getContent()
                );
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            } catch (BookNotFoundException e) {
                throw new RuntimeException(e);
            }
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

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable String username) {
        List<Review> reviews = reviewService.getReviewsByUserUsername(username);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/user/{username}/book/{isbn}")
    public ResponseEntity<Review> getUserReviewForBook(@PathVariable String username, @PathVariable String isbn) {
        Optional<Review> review = reviewService.getUserReviewByBookIsbn(username, isbn);
        return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<Review>> getReviewsForBook(@PathVariable String isbn) {
        List<Review> reviews = reviewService.getReviewsByBookIsbn(isbn);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
