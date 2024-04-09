package com.datmt.springdatapostgres.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Laptop extends Thing {

    private String name;
    @ElementCollection
    private List<String> keys;
}
