package com.datmt.springdatajpatransactionaldemo.service.mapping;

import com.datmt.springdatajpatransactionaldemo.model.car.one_many.Maker;
import com.datmt.springdatajpatransactionaldemo.repository.mapping.MakerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MakerService {
    private final MakerRepository makerRepository;

    public MakerService(MakerRepository makerRepository) {
        this.makerRepository = makerRepository;
    }

    public Maker save(Maker maker) {
        return makerRepository.save(maker);
    }

    public Maker findByName(String name) {
        return makerRepository.findByName(name).orElse(null);
    }
}
