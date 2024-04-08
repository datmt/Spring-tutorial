package com.datmt.spring.mapstructtutorial.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
   private long id;
    private String make;
    private int numberOfSeats;
    private CarType type;
    private Engine engine;
}
