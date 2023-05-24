package com.datmt.springdatajpatransactionaldemo.books;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToMany
    private List<Author> authors;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

}
