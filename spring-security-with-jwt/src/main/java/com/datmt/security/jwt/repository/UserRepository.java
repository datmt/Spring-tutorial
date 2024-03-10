package com.datmt.security.jwt.repository;

import com.datmt.security.jwt.model.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);     
}
