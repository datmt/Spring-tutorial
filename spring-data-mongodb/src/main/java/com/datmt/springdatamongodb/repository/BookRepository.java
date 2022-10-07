package com.datmt.springdatamongodb.repository;

import com.datmt.springdatamongodb.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
