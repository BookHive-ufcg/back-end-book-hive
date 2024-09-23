package com.bookhive.ufcg.bookhive.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bookhive.ufcg.bookhive.model.Review;

@Repository
public class ReviewRepository {
	
	private final Map<Long, Review> reviewStorage = new HashMap<>();
    private final Map<String, List<Review>> reviewsByBookIsbn = new HashMap<>();
    private final Map<String, List<Review>> reviewsByUserUsername = new HashMap<>();
    private Long idCounter = 1L;  // pra gerar ids únicos para cada review
    
    public Review save(Review review) {
        review.setId(idCounter++);
        
        reviewStorage.put(review.getId(), review);
        
        reviewsByBookIsbn.computeIfAbsent(review.getBook().getisbn(), k -> new ArrayList<>()).add(review);

        reviewsByUserUsername.computeIfAbsent(review.getUser().getUsername(), k -> new ArrayList<>()).add(review);

        return review;
    }
    
    public List<Review> findByBookIsbn(String isbn){
        return reviewsByBookIsbn.getOrDefault(isbn, new ArrayList<>());
    }
    
    public List<Review> findByUserUsername(String username){
        return reviewsByUserUsername.getOrDefault(username, new ArrayList<>());
    }
    
    public Optional<Review> findByUserUsernameAndBookIsbn(String username, String isbn) {
        List<Review> userReviews = reviewsByUserUsername.getOrDefault(username, new ArrayList<>());
        return userReviews.stream()
                .filter(review -> review.getBook().getisbn().equals(isbn))
                .findFirst();
    }
    
    public boolean existsById(Long id) {
        return reviewStorage.containsKey(id);
    }
    
    public void deleteById(Long id) {
        Review reviewToDelete = reviewStorage.remove(id);
        if (reviewToDelete != null) {
            reviewsByBookIsbn.getOrDefault(reviewToDelete.getBook().getisbn(), new ArrayList<>())
                    .removeIf(review -> review.getId().equals(id));
            reviewsByUserUsername.getOrDefault(reviewToDelete.getUser().getUsername(), new ArrayList<>())
                    .removeIf(review -> review.getId().equals(id));
        }
    }
}

