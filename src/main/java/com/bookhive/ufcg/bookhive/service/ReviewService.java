package com.bookhive.ufcg.bookhive.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

 
    public void addReview(ReviewDTO reviewDTO) throws ReviewConflictException,UserNotFoundException, BookNotFoundException {

    	if(reviewRepository.findByUsernameAndIsbn(reviewDTO.getUsernameUser(), reviewDTO.getBookIsbn()).isPresent()) {
    		throw new ReviewConflictException("A resenha já existe");
    	}

        User user = userRepository.findById(reviewDTO.getUsernameUser())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        Book book = bookService.getOrCreateBook(reviewDTO.getBookIsbn());

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
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review: " + id + " não encontrado."));
    }
    
    public List<Review> listReviews() {
        return new ArrayList<>(reviewRepository.findAll());
    }

    public List<Review> getReviewsByBookIsbn(String isbn) {
        return new ArrayList<>(reviewRepository.findByBookIsbn(isbn));
    }
    public Optional<Review> getUserReviewByBookIsbn(String username, String isbn) {
        return reviewRepository.findByUsernameAndIsbn(username, isbn);
    }

    public List<Review> getReviewsByUserUsername(String username) {
        return new ArrayList<>(reviewRepository.findByUsername(username));
    }


    private ReviewDTO convertToReviewDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getUserNameUser().getUsername(),
                review.getBookIsbn().getisbn(),
                review.getStartDate(),
                review.getEndDate(),
                review.getRating(),
                review.getContent()

        );
    }
}
