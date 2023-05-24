package com.datmt.springdatajpatransactionaldemo.model.car.one_many;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "car")
public class Car {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne()
    private Maker maker;
    private String name;

    public Long getId() {
        return id;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
