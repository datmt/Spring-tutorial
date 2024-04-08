package com.datmt.springdatapostgres;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datmt.springdatapostgres.model.Author;
import com.datmt.springdatapostgres.model.Book;
import com.datmt.springdatapostgres.repository.CommonRepository;

@SpringBootTest
public class TestCreateAuthor {
    @Autowired
    private CommonRepository commonRepository;

    @Test
    public void runTest() {
        var author = new Author();
        var book = new Book();
        book.setPrice(100.0);
        book.setTitle("How to make 100");
        author.setName("James");

        author.setBooks(List.of(book));
        var savedAuthor = commonRepository.save(author);
        book.setAuthor(savedAuthor);
        commonRepository.save(book);
    }

}
