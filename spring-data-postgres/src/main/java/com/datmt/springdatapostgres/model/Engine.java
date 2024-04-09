package com.datmt.springdatapostgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engine {
    private String name;
    private String manufacturer;
    private int power;
}
