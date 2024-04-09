package com.datmt.springdatapostgres.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Chapter {
    private String title;
    private int pages;
}
