package com.datmt.springdatapostgres.model.o2m;

import com.datmt.springdatapostgres.model.Thing;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Celeb extends Thing {

    private String name;

    @ManyToOne
    private Manager manager;
}
