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



    @PostMapping(value = "user", consumes = "multipart/form-data")
    public ResponseEntity<String> addUser(
            @RequestPart("userDTO") String userDTOJson,
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture) {
        try {
            // Converte o JSON recebido para um objeto UserDTO
            ObjectMapper objectMapper = new ObjectMapper();
            UserDTO userDTO = objectMapper.readValue(userDTOJson, UserDTO.class);

            // Inicializa a variável de bytes da foto
            byte[] photoBytes = null;

            // Verifica se a foto foi enviada
            if (profilePicture != null) {
                photoBytes = profilePicture.getBytes();
            }

            // Chama o serviço para adicionar o usuário, passando a foto (ou null)
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
            UserDTO userDTO = new UserDTO(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getDateOfBirth(),
                    user.getPassword()
            );

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        // Converte a lista de User para UserDTO
        List<UserDTO> userDTOs = users.stream()
                .map(user -> new UserDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getDateOfBirth(),
                        user.getPassword()
                ))
                .toList();

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }


    @GetMapping(value = "user/{username}/profilePicture", produces = "image/jpeg")
    public ResponseEntity<byte[]> getUserProfilePicture(@PathVariable("username") String username) {
        try {
            User user = userService.getUserByUsername(username);

            byte[] photo = user.getProfilePicture();
            if (photo == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok()
                    .header("Content-Disposition", "inline; filename=\"" + username + ".jpg\"")
                    .body(photo);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
