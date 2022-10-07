package com.datmt.springdatamongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document
public class Publisher {
    private String _id = UUID.randomUUID().toString();
    private String name;
    private String about;
}
