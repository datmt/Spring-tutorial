package com.example.unittestcucumber.repository;

import com.example.unittestcucumber.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
