package com.datmt.springdatamongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class Book {
    @Id
    private String _id;

    private String title;

    private int pages;

    private List<Publisher> publishers;
}
