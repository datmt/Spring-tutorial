package com.datmt.springdatajpatransactionaldemo.service.mapping;

import com.datmt.springdatajpatransactionaldemo.model.car.one_many.Car;
import com.datmt.springdatajpatransactionaldemo.repository.mapping.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }
}
