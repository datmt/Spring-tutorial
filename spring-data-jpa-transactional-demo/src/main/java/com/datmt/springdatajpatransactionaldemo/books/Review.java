package com.datmt.springdatajpatransactionaldemo.books;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne
    private Book book;
}
