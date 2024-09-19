package com.bookhive.ufcg.bookhive.repository;

import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookhive.ufcg.bookhive.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
