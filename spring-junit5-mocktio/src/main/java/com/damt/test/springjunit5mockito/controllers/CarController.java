package com.damt.test.springjunit5mockito.controllers;

import com.damt.test.springjunit5mockito.models.Car;
import com.damt.test.springjunit5mockito.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping("/")
    public Car create(@RequestBody Car car) {
        return carService.create(car);
    }

    @GetMapping("/{id}")
    public Car find(@PathVariable Long id) {
        return carService.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @PutMapping("/")
    public Car update(@RequestBody Car car) {
        return carService.update(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.ok("Car deleted");
    }
}
