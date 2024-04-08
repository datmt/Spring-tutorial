package com.datmt.springdatapostgres.model;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends Thing {

    private String name;
    @Embedded
    private Engine engine;
}
