package com.damt.test.springjunit5mockito.services;

import com.damt.test.springjunit5mockito.SpringJunit5MockitoApplication;
import com.damt.test.springjunit5mockito.models.Car;
import com.damt.test.springjunit5mockito.repositories.CarRepository;
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

    }

    @Test
    @DisplayName("Cannot save car since name is null")
    void create() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }
}