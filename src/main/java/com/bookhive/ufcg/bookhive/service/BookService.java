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

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public void addBook(BookDTO bookDTO) throws BookConflictException {
        if(repository.findById(bookDTO.getIsbn()).isPresent()){
                throw new BookConflictException("livro com ISBN: " + bookDTO.getIsbn() + " Já cadastrado");}
        Book book = new Book(bookDTO.getIsbn(), bookDTO.getRating());
        this.repository.save(book);
    }


    public void updateBook(String isbn, Float rating) throws BookNotFoundException {
        Book book = repository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("livro com ISBN: " + isbn + " Não Encontrado"));
        book.setisbn(isbn);
        //precisa estudar melhor essa logica,para atualizar corretamente
        book.setRating(rating);
        repository.save(book);
    }

    public void removeBook(String isbn) throws BookNotFoundException {
        Book book = repository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("livro com ISBN: " + isbn + " Não Encontrado"));
        repository.delete(book);
    }

    public Book getBook(String isbn) throws BookNotFoundException {
        return repository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("livro com ISBN: " + isbn + " Não Encontrado"));
    }

    public List<Book> listBooks() {
        return new ArrayList<>(repository.findAll());
    }
    
    public Book getOrCreateBook(String isbn) {
        return repository.findById(isbn)
                .orElseGet(() -> {
                    Book newBook = new Book(isbn);
                    return repository.save(newBook);
                });
    }

}
