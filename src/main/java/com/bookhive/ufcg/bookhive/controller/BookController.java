package com.bookhive.ufcg.bookhive.controller;


import com.bookhive.ufcg.bookhive.dto.BookDTO;
import com.bookhive.ufcg.bookhive.exception.BookConflictException;
import com.bookhive.ufcg.bookhive.exception.BookNotFoundException;
import com.bookhive.ufcg.bookhive.model.Book;
import com.bookhive.ufcg.bookhive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createOrUpdateBook(@RequestBody BookDTO bookDTO) {
        BookDTO savedBook = bookService.createOrUpdateBook(bookDTO);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) {
        Optional<BookDTO> bookDTO = bookService.getBookByIsbn(isbn);
        return bookDTO.map(ResponseEntity::ok)
                      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        if (bookService.deleteBookByIsbn(isbn)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{isbn}/exists")
    public ResponseEntity<Boolean> bookExists(@PathVariable String isbn) {
        boolean exists = bookService.bookExists(isbn);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}
