package com.datmt.spring.mapstructtutorial.dto;

import com.datmt.spring.mapstructtutorial.model.EngineType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EngineDTO {
    private EngineType type;
    private int horsePower;
}
