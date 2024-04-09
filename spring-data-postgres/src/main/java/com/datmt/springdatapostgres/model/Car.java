package com.datmt.springdatapostgres.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
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
    @AttributeOverride(name = "name", column = @Column(name = "engine_name"))
    private List<Engine> engines = new ArrayList<>();
}
