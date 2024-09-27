package com.bookhive.ufcg.bookhive.repository;


import com.bookhive.ufcg.bookhive.model.Book;
import com.bookhive.ufcg.bookhive.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

}

