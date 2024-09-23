package com.bookhive.ufcg.bookhive.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookhive.ufcg.bookhive.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

