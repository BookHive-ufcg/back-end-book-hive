package com.bookhive.ufcg.bookhive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.util.UriComponentsBuilder;

import com.bookhive.ufcg.bookhive.dto.UserDTO;
import com.bookhive.ufcg.bookhive.model.User;
import com.bookhive.ufcg.bookhive.exception.UserNotFoundException;
import com.bookhive.ufcg.bookhive.exception.UserConflictException;
import com.bookhive.ufcg.bookhive.service.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
		return new ResponseEntity<>("Usuário cadastrado com sucesso", HttpStatus.CREATED);
    }

    @RequestMapping(value = "user/{username}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO, @PathVariable("username") String username) throws UserNotFoundException {
        userService.updateUser(username,userDTO);
		return new ResponseEntity<>("Usuário atualizado com sucesso.", HttpStatus.OK);
    }

    @RequestMapping(value = "user/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeUser(@PathVariable("username") String username) throws UserNotFoundException {
        userService.deleteUserByUsername(username);
		return new ResponseEntity<>("Usuário removido com sucesso.", HttpStatus.OK);
    }

    @RequestMapping(value = "user/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("username") String username) throws UserNotFoundException {
        Optional<UserDTO> user = userService.getUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = (List<User>) userService.listUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
