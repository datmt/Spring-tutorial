package com.damt.test.springjunit5mockito3.services2;

import com.damt.test.springjunit5mockito.SpringJunit5MockitoApplication;
import com.damt.test.springjunit5mockito.models.Car;
import com.damt.test.springjunit5mockito.repositories.CarRepository;
import com.damt.test.springjunit5mockito.services.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringJunit5MockitoApplication.class)
class CarServiceTest {
    @MockBean
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    Car newCar;
    Car savedCar;


    @BeforeEach
    void setUp() {
        newCar = new Car();
        newCar.setName("Corolla");
        newCar.setModel("Toyota");
        newCar.setMaker("Toyota");
        newCar.setColor("White");

        savedCar = new Car();
        savedCar.setId(1L);
        savedCar.setName("Corolla");
        savedCar.setModel("Toyota");
        savedCar.setMaker("Toyota");
        savedCar.setColor("White");

        when(carRepository.save(newCar)).thenReturn(savedCar);
    }

    @Test
    @DisplayName("Cannot save car since name is null")
    void create() {
        var car = new Car();
        car.setColor("White");
        car.setMaker("Toyota");
        car.setModel("Corolla");

        Assertions.assertThrows(RuntimeException.class, () -> carService.create(car));
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }
}