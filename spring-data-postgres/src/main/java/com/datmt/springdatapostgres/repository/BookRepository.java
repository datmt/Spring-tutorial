package com.datmt.springdatapostgres.repository;

import com.datmt.springdatapostgres.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
