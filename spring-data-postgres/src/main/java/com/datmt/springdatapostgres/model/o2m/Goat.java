package com.datmt.springdatapostgres.model.o2m;

import com.datmt.springdatapostgres.model.Thing;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Goat extends Thing {

    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;
}
