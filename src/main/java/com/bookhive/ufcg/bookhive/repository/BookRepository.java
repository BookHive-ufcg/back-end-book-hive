package com.bookhive.ufcg.bookhive.repository;


import com.bookhive.ufcg.bookhive.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {


}

