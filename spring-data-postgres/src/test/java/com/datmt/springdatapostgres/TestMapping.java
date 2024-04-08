package com.datmt.springdatapostgres;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datmt.springdatapostgres.model.Author;
import com.datmt.springdatapostgres.model.Book;
import com.datmt.springdatapostgres.model.Car;
import com.datmt.springdatapostgres.model.Engine;
import com.datmt.springdatapostgres.repository.CommonRepository;

@SpringBootTest
public class TestMapping {
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

    @Test
    @DisplayName("Test embeddable")
    void testEmbeddable() {
        var engine = new Engine("V8", 1000);
        var car = new Car("BMW", engine);
        commonRepository.save(car);
    }

}
