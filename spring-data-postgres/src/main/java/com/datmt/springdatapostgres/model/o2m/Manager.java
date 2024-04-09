package com.datmt.springdatapostgres.model.o2m;

import com.datmt.springdatapostgres.model.Thing;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Manager extends Thing {
    private String name;

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
