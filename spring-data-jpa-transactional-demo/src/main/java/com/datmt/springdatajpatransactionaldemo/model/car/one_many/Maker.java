package com.datmt.springdatajpatransactionaldemo.model.car.one_many;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "maker")
public class Maker {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "maker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Car> cars;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }
}
