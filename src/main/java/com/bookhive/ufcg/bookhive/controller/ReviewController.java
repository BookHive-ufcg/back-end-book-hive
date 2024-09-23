package com.bookhive.ufcg.bookhive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookhive.ufcg.bookhive.dto.ReviewDTO;
import com.bookhive.ufcg.bookhive.exception.ReviewNotFoundException;
import com.bookhive.ufcg.bookhive.exception.ReviewConflictException;
import com.bookhive.ufcg.bookhive.model.Book;
import com.bookhive.ufcg.bookhive.model.Review;
import com.bookhive.ufcg.bookhive.model.User;
import com.bookhive.ufcg.bookhive.repository.BookRepository;
import com.bookhive.ufcg.bookhive.repository.ReviewRepository;
import com.bookhive.ufcg.bookhive.repository.UserRepository;
import com.bookhive.ufcg.bookhive.service.ReviewService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@CrossOrigin
public class ReviewController {

	@Autowired
	private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    
    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<Review>> getReviewsByBook(@PathVariable String isbn) {
        List<Review> reviews = reviewRepository.findByBookIsbn(isbn);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable String username) {
        List<Review> reviews = reviewRepository.findByUserUsername(username);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
    @GetMapping("/user/{username}/book/{isbn}")
    public ResponseEntity<Review> getUserReviewForBook(@PathVariable String username, @PathVariable String isbn) {
        Optional<Review> review = reviewRepository.findByUserUsernameAndBookIsbn(username, isbn);
        if (review.isPresent()) {
            return new ResponseEntity<>(review.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<String> createReview(
            @RequestParam String isbn,
            @RequestParam String username,
            @RequestParam Integer rating,
            @RequestParam String comments) {

        Optional<Book> bookOptional = bookRepository.findById(isbn);
        Optional<User> userOptional = userRepository.findById(username);

        if (bookOptional.isPresent() && userOptional.isPresent()) {
            Review review = new Review(bookOptional.get(), userOptional.get(), rating, comments);
            reviewRepository.save(review);
            return new ResponseEntity<>("Review criada com sucesso", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Livro ou Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (!reviewRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reviewRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
