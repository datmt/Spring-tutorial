package com.datmt.spring.mapstructtutorial.dto;

import com.datmt.spring.mapstructtutorial.mapper.CarMapper;
import com.datmt.spring.mapstructtutorial.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarMapperTest {

    private Car hondaDieselCar;
    private Car gmPetrolCar;
    private Car bydElectricCar;
    private Car toyotaHybridCar;

    @BeforeEach
    void setup() {
        var hondaEngineMaker = new EngineMaker("Honda", "Japan", 1948);
        var gmEngineMaker = new EngineMaker("GM", "USA", 1908);
        var bydEngineMaker = new EngineMaker("BYD", "China", 2003);
        var toyotaEngineMaker = new EngineMaker("Toyota", "Japan", 1937);

        var hondaEngine = new Engine(EngineType.DIESEL, 150, hondaEngineMaker);
        var gmEngine = new Engine(EngineType.GASOLINE, 200, gmEngineMaker);
        var bydEngine = new Engine(EngineType.ELECTRIC, 250, bydEngineMaker);
        var toyotaEngine = new Engine(EngineType.HYBRID, 300, toyotaEngineMaker);


        hondaDieselCar = new Car(1L, "Honda", 5, CarType.SEDAN, hondaEngine);
        gmPetrolCar = new Car(2L, "GM", 7, CarType.SUV, gmEngine);
        bydElectricCar = new Car(3L, "BYD", 5, CarType.HATCHBACK, bydEngine);
        toyotaHybridCar = new Car(4L, "Toyota", 5, CarType.SEDAN, toyotaEngine);
    }

    @Test
    void testConversion() {
        var hondaCarDto = CarMapper.INSTANCE.carToCarDto(hondaDieselCar);
        var gmCarDto = CarMapper.INSTANCE.carToCarDto(gmPetrolCar);
        var bydCarDto = CarMapper.INSTANCE.carToCarDto(bydElectricCar);
        var toyotaCarDto = CarMapper.INSTANCE.carToCarDto(toyotaHybridCar);

        System.out.println(hondaCarDto);
        System.out.println(gmCarDto);
        System.out.println(bydCarDto);
        System.out.println(toyotaCarDto);
    }
}