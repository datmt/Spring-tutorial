package com.datmt.spring.mapstructtutorial.dto;

import com.datmt.spring.mapstructtutorial.model.CarType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CarDisplayDTO {
    private long id;
    private String manufacturer;
    private int seatCount;
    private CarType type;
    private String engineMaker;
    private EngineDTO engine;
    private float tax; //5% for electric cars, 10% for petrol cars, 15% for diesel cars
}
