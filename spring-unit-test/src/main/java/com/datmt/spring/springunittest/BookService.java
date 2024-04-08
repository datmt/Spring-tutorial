package com.datmt.spring.springunittest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
