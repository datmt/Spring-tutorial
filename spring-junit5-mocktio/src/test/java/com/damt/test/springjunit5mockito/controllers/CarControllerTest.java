package com.damt.test.springjunit5mockito.controllers;

import com.damt.test.springjunit5mockito.models.Car;
import com.damt.test.springjunit5mockito.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@AutoConfigureMockMvc
@SpringBootTest
class CarControllerTest {
    //
    @Mock
    private CarService carService;

    private CarController carController;

    @BeforeEach
    void setUp() {
        openMocks(this);
        carController = new CarController(carService);
    }

    @Test
    void create_shouldReturnNewCar() {
        Car car = new Car(null, "Toyota", "Camry", "Toyota", "White");

        when(carService.create(any(Car.class))).thenReturn(car);

        Car result = carController.create(car);

        assertEquals(car, result);
        verify(carService, times(1)).create(any(Car.class));
    }

    @Test
    void find_withValidId_shouldReturnCar() {
        long carId = 1L;
        Car car = new Car(null, "Toyota", "Camry", "Toyota", "White");

        when(carService.findById(eq(carId))).thenReturn(Optional.of(car));

        Car result = carController.find(carId);

        assertEquals(car, result);
        verify(carService, times(1)).findById(eq(carId));
    }

    @Test
    void find_withInvalidId_shouldThrowException() {
        long carId = 1L;

        when(carService.findById(eq(carId))).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carController.find(carId));

        assertEquals("Car not found", exception.getMessage());
        verify(carService, times(1)).findById(eq(carId));
    }

    @Test
    void update_shouldReturnUpdatedCar() {
        Car car = new Car(null, "Toyota", "Camry", "Toyota", "White");

        when(carService.update(any(Car.class))).thenReturn(car);

        Car result = carController.update(car);

        assertEquals(car, result);
        verify(carService, times(1)).update(any(Car.class));
    }

    @Test
    void delete_withValidId_shouldReturnSuccessMessage() {
        long carId = 1L;

        ResponseEntity<String> response = carController.delete(carId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Car deleted", response.getBody());
        verify(carService, times(1)).delete(eq(carId));
    }
}