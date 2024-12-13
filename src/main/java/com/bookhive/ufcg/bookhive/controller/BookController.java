package com.bookhive.ufcg.bookhive.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookhive.ufcg.bookhive.dto.BookDTO;
import com.bookhive.ufcg.bookhive.exception.BookConflictException;
import com.bookhive.ufcg.bookhive.exception.BookNotFoundException;
import com.bookhive.ufcg.bookhive.model.Book;
import com.bookhive.ufcg.bookhive.service.BookService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;


    @RequestMapping(value = "book", method = RequestMethod.POST)
    public ResponseEntity<String> addbook(@RequestBody BookDTO bookDTO) {
        try {
            bookService.addBook(bookDTO);
            return new ResponseEntity<>("Livro cadastrado com sucesso", HttpStatus.CREATED);
        } catch (BookConflictException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "book/{isbn}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBook(@RequestBody BookDTO bookDTO, @PathVariable("isbn") String isbn) {
        try {
            bookService.updateBook(isbn, bookDTO.getRating());
            return new ResponseEntity<>("Usuário atualizado com sucesso.", HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } 
    }

    @RequestMapping(value = "book/{isbn}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removebook(@PathVariable("isbn") String isbn) {
        try {
            bookService.removeBook(isbn);
            return new ResponseEntity<>("Usuário removido com sucesso.", HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "book/{isbn}", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@PathVariable("isbn") String isbn) {
        try {
            Book book = bookService.getBook(isbn);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> listbooks() {
        List<Book> books = bookService.listBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
