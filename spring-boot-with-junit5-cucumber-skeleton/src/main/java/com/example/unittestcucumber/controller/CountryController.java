package com.example.unittestcucumber.controller;

import com.example.unittestcucumber.model.Country;
import com.example.unittestcucumber.repository.CountryRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @GetMapping
    public String hello() {
        return "Hey you";
    }


    @GetMapping("/{id}")
    public String helloWithName(@PathVariable Long id) {
        return "Hello from " + countryRepository.findById(id).get().getName();
    }

    @PostMapping()
    public Country createCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }

}
