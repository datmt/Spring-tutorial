package com.datmt.spring.mapstructtutorial.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EngineMaker {
    private String name;
    private String country;
    private int establishedYear;
}
