package com.bookhive.ufcg.bookhive.service;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookhive.ufcg.bookhive.dto.ReviewDTO;
import com.bookhive.ufcg.bookhive.dto.UserDTO;
import com.bookhive.ufcg.bookhive.model.Review;
import com.bookhive.ufcg.bookhive.model.User;
import com.bookhive.ufcg.bookhive.repository.UserRepository;
import com.bookhive.ufcg.bookhive.exception.UserNotFoundException;
import com.bookhive.ufcg.bookhive.exception.UserConflictException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getFirstName(), 
                userDTO.getLastName(), 
                userDTO.getUsername(), 
                userDTO.getDateOfBirth(), 
                userDTO.getPassword()
        );
        userRepository.save(user);
        return userDTO;
    }
    
    public Optional<UserDTO> getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findById(username);

        return userOptional.map(user -> {
            List<ReviewDTO> reviewDTOs = user.getReviews().stream()
                    .map(this::convertToReviewDTO)
                    .collect(Collectors.toList());

            return new UserDTO(
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
                    user.getDateOfBirth(),
                    reviewDTOs
            );
        });
    }
    
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
    
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserByUsername(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
            return true;
        }
        return false;
    }
    
    public UserDTO updateUser(String username, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPassword(userDTO.getPassword());
            user.setDateOfBirth(userDTO.getDateOfBirth());
            userRepository.save(user);
            return userDTO;
        }
        throw new RuntimeException("User not found");
    }
}
