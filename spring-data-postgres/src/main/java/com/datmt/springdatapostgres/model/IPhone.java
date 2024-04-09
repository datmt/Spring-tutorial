package com.datmt.springdatapostgres.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class IPhone extends Phone {
    private String model;
    private boolean withIcloud;
}
