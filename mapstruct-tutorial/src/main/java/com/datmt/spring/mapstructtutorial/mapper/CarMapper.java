package com.datmt.spring.mapstructtutorial.mapper;

import com.datmt.spring.mapstructtutorial.dto.CarDisplayDTO;
import com.datmt.spring.mapstructtutorial.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDisplayDTO carToCarDto(Car car);
}
