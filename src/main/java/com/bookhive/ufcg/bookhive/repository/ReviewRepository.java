package com.bookhive.ufcg.bookhive.repository;

import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bookhive.ufcg.bookhive.model.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query("SELECT r FROM Review r WHERE r.user.username = :username")
    List<Review> findByUsername(@Param("username") String username);

    @Query("SELECT r FROM Review r WHERE r.book.isbn = :isbn")
    List<Review> findByBookIsbn(@Param("isbn") String isbn);

    @Query("SELECT r FROM Review r WHERE r.user.username = :username AND r.book.isbn = :isbn")
    Optional<Review> findByUsernameAndIsbn(@Param("username") String username, @Param("isbn") String isbn);
}


