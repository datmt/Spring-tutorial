package com.datmt.springdatamongodb;

import com.datmt.springdatamongodb.model.Book;
import com.datmt.springdatamongodb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/list")
    public List<Book> getAll() {
       return bookRepository.findAll();
    }

    @PostMapping()
    public Book create(@RequestBody Book book) {
       return bookRepository.save(book);
    }
}
