package com.datmt.spring.springunittest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public Book createBook(Book book) {
        return bookService.save(book);
    }

    @GetMapping("/{id}")
    public Book getBook(Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/list")
    public List<Book> listBooks() {
        return bookService.findAll();
    }
}
