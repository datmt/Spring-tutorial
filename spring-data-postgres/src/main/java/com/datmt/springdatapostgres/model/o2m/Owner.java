package com.datmt.springdatapostgres.model.o2m;

import java.util.ArrayList;
import java.util.List;

import com.datmt.springdatapostgres.model.Thing;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Owner extends Thing {
    private String name;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Goat> goats = new ArrayList<>();
}
