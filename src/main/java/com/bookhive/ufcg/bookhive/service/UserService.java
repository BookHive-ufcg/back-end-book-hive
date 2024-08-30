package com.bookhive.ufcg.bookhive.service;

import java.util.ArrayList;
//import java.util.Collection;
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
        if (this.userRep.hasUser(userDTO.getUsername())) {
            throw new UserConflictException("Nome de usuário já existe.");
        }
        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUsername(), userDTO.getDateOfBirth(), userDTO.getPassword());

        this.userRep.addUser(user);
    }

    public boolean verifiesUser(String username) {
        return this.userRep.hasUser(username);
    }

	public void updateUser(String username, String firstName, String lastName) throws UserNotFoundException {
		if (!this.userRep.hasUser(username)) {
            throw new UserNotFoundException("Usuário: " + username + " não encontrado."); 
        } 
        this.userRep.updateUser(username,firstName,lastName);
	}
	
	
	public void removeUser(String username) throws UserNotFoundException {
		if (!this.userRep.hasUser(username)) {
            throw new UserNotFoundException("Usuário: " + username + " não encontrado."); 
        } 
        this.userRep.removeUser(username);
    }
	public User getUserByUsername(String username) throws UserNotFoundException {
		User user = this.userRep.getUser(username);
		if(user == null) throw new UserNotFoundException("Usuário: " + username + " não encontrado."); 
		
		return user;	
	}
	
	public List<String> listUsers() {
		return new ArrayList<String>(userRep.listUsers());
	}
}