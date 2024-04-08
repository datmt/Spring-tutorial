package com.datmt.springdatapostgres;

import com.datmt.springdatapostgres.model.Author;
import com.datmt.springdatapostgres.model.Book;
import com.datmt.springdatapostgres.repository.AuthorRepository;
import com.datmt.springdatapostgres.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringDataPostgresApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataPostgresApplication.class, args);
    }

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;
    @Override
    public void run(String... args) throws Exception {

        var author = authorRepository.save(new Author(null, "datmt", List.of()));
        authorRepository.save(author);
        bookRepository.saveAll(List.of(
                new Book(null, "Spring Boot", author, 100),
                new Book(null, "Spring Data", author, 200),
                new Book(null, "Spring Security", author, 300)
        ));


        var authors = authorRepository.findByName("datmt");

        var firstAuthor = authors.get(0);
        System.out.println(firstAuthor.getBooks());


//
//        authors.forEach(System.out::println);
    }
}
