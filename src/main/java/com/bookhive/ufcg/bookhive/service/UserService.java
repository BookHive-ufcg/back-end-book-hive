package com.bookhive.ufcg.bookhive.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookhive.ufcg.bookhive.dto.UserDTO;
import com.bookhive.ufcg.bookhive.model.User;
import com.bookhive.ufcg.bookhive.repository.UserRepository;
import com.bookhive.ufcg.bookhive.exception.UserNotFoundException;
import com.bookhive.ufcg.bookhive.exception.UserConflictException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRep;

    public void addUser(UserDTO userDTO) throws UserConflictException {
        if (!this.userRep.existsById(userDTO.getUsername())) {
                User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUsername(), userDTO.getDateOfBirth(), userDTO.getPassword());
                this.userRep.save(user);
            } else {
                throw new UserConflictException("Usuário " + userDTO.getUsername() + " já existe");
            }
        }

	public void updateUser(String username, String firstName, String lastName) throws UserNotFoundException {
        User user = userRep.findById(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário: " + username + " não encontrado."));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRep.save(user);
	}

	public void removeUser(String username) throws UserNotFoundException {
        User user = userRep.findById(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário: " + username + " não encontrado."));
        userRep.delete(user);
    }
    
	public User getUserByUsername(String username) throws UserNotFoundException {
        User user = userRep.findById(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário: " + username + " não encontrado."));
        return user;
    }

	public List<User> listUsers() {
        return new ArrayList<>(userRep.findAll());
    }
}
