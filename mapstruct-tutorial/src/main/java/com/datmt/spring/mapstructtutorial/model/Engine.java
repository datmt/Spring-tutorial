package com.datmt.spring.mapstructtutorial.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Engine {
    private EngineType type;
    private int horsePower;
    private EngineMaker maker;
}
