package com.datmt.springdatamongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Car {
    @Id
    private String _id;

    private String name;

    private String maker;

    private List<String> categories;
}
