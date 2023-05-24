package com.datmt.springdatajpatransactionaldemo.books;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
