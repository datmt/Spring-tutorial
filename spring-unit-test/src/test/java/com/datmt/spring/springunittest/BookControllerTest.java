package com.datmt.spring.springunittest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("Test create book success")
    void createBook() {

        var book = new Book();
        book.setTitle("Spring Unit Test");

        var savedBook = bookController.createBook(book);

        var foundBook = bookController.getBook(savedBook.getId());


        Assertions.assertEquals(savedBook.getId(), foundBook.getId());
        Assertions.assertEquals(savedBook.getTitle(), foundBook.getTitle());
    }

    @Test
    void getBook() {
    }

    @Test
    @DisplayName("Test list books success, return correct number of books")
    void listBooks() {
        var book1 = new Book();
        book1.setTitle("Spring Unit Test");

        var book2 = new Book();
        book2.setTitle("Spring Unit Test 2");

        bookController.createBook(book1);
        bookController.createBook(book2);

        var books = bookController.listBooks();

        Assertions.assertEquals(2, books.size());
    }
}