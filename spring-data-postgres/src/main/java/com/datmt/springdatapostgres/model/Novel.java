package com.datmt.springdatapostgres.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Novel extends Thing {

    private String title;
    @ElementCollection
    List<Chapter> chapters;
}
