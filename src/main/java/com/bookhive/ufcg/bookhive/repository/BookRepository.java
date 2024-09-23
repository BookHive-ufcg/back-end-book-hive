package com.bookhive.ufcg.bookhive.repository;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookhive.ufcg.bookhive.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	public final Map<String, Book> bookStorage = new HashMap<>();

    public default Optional<Book> findById(String isbn) {
        return Optional.ofNullable(bookStorage.get(isbn));
    }
}

