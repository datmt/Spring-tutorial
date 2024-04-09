package com.datmt.springdatapostgres.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car extends Thing {

    private String name;
    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "engine_name"))
    private Engine engine;
}
