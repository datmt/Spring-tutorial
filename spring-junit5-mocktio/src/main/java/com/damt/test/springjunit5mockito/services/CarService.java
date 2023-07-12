package com.damt.test.springjunit5mockito.services;

import com.damt.test.springjunit5mockito.models.Car;
import com.damt.test.springjunit5mockito.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;


    public Car create(Car car) {
        //assert all fields except id are not null
        if (car.getId() != null) {
            throw new RuntimeException("Id must be null");
        }
        verifyCar(car);

        return carRepository.save(car);
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    //update car
    public Car update(Car car) {
        //assert all fields except id are not null
        if (car.getId() == null) {
            throw new RuntimeException("Id must not be null");
        }
        verifyCar(car);

        //check if car exists
        carRepository.findById(car.getId()).orElseThrow(() -> new RuntimeException("Car not found"));

        return carRepository.save(car);
    }

    private void verifyCar(Car car) {
        if (car.getName() == null) {
            throw new RuntimeException("Name must not be null");
        }
        if (car.getModel() == null) {
            throw new RuntimeException("Model must not be null");
        }
        if (car.getMaker() == null) {
            throw new RuntimeException("Maker must not be null");
        }
        if (car.getColor() == null) {
            throw new RuntimeException("Color must not be null");
        }
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
