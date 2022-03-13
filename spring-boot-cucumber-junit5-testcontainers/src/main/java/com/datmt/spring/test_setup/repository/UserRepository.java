package com.datmt.spring.test_setup.repository;

import com.datmt.spring.test_setup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByName(String name);
}