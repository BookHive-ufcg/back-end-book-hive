package com.bookhive.ufcg.bookhive.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookhive.ufcg.bookhive.dto.UserDTO;
import com.bookhive.ufcg.bookhive.model.User;
import com.bookhive.ufcg.bookhive.exception.UserNotFoundException;
import com.bookhive.ufcg.bookhive.exception.UserConflictException;
import com.bookhive.ufcg.bookhive.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestParam("profilePicture") MultipartFile profilePicture,
                                          @RequestParam("user") UserDTO userJson) {
        try {
            // Converte o JSON do usuário em um objeto UserDTO
            ObjectMapper objectMapper = new ObjectMapper();
            UserDTO userDTO = objectMapper.readValue(userJson, UserDTO.class);

            // Salva a foto como um array de bytes
            byte[] photoBytes = profilePicture.getBytes();

            // Chama o serviço para adicionar o usuário com a foto
            userService.addUser(userDTO, photoBytes);

            return new ResponseEntity<>("Usuário cadastrado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "user/{username}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO, @PathVariable("username") String username) {
        try {
            userService.updateUser(username, userDTO.getFirstName(), userDTO.getLastName());
            return new ResponseEntity<>("Usuário atualizado com sucesso.", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "user/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeUser(@PathVariable("username") String username) {
        try {
            userService.removeUser(username);
            return new ResponseEntity<>("Usuário removido com sucesso.", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "user/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {
        try {
            User user = userService.getUserByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.listUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
