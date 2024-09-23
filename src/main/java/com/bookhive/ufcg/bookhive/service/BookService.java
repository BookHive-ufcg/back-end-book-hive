package com.bookhive.ufcg.bookhive.service;

import com.bookhive.ufcg.bookhive.dto.BookDTO;

import com.bookhive.ufcg.bookhive.exception.BookConflictException;
import com.bookhive.ufcg.bookhive.exception.BookNotFoundException;
import com.bookhive.ufcg.bookhive.model.Book;
import com.bookhive.ufcg.bookhive.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

 @Autowired
    private BookRepository bookRepository;

    public BookDTO createOrUpdateBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getIsbn(), bookDTO.getRating());
        bookRepository.save(book);
        return bookDTO;
    }

    public Optional<BookDTO> getBookByIsbn(String isbn) {
        Optional<Book> bookOptional = bookRepository.findById(isbn);
        return bookOptional.map(book -> new BookDTO(book.getisbn(), book.getRating(), null));
    }

    public boolean deleteBookByIsbn(String isbn) {
        if (bookRepository.existsById(isbn)) {
            bookRepository.deleteById(isbn);
            return true;
        }
        return false;
    }

    public boolean bookExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

}
