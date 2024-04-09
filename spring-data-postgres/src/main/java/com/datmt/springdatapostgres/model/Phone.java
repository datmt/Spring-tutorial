package com.datmt.springdatapostgres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
public class Phone extends Thing {
    private String name;
    private String brand;
    private int price;
}
