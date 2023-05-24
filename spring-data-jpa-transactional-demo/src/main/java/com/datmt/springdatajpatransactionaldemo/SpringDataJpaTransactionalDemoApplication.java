package com.datmt.springdatajpatransactionaldemo;

import com.datmt.springdatajpatransactionaldemo.model.car.one_many.Car;
import com.datmt.springdatajpatransactionaldemo.model.car.one_many.Maker;
import com.datmt.springdatajpatransactionaldemo.service.mapping.CarService;
import com.datmt.springdatajpatransactionaldemo.service.mapping.MakerService;
import com.datmt.springdatajpatransactionaldemo.service.tx.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaTransactionalDemoApplication implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    @Autowired
    MakerService makerService;

    @Autowired
    CarService carService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaTransactionalDemoApplication.class, args);
    }

    @Override
    //adding this @Transactional annotation fix the lazyfetch exception
    @Transactional
    public void run(String... args) throws Exception {
        var maker = new Maker();
        maker.setName("BMW");
        maker.setCars(List.of());
        var savedMaker = makerService.save(maker);


        var car1 = new Car();
        car1.setName("X5");
        car1.setMaker(savedMaker);

        var saved1 = carService.save(car1);

        var car2 = new Car();
        car2.setName("X6");
        car2.setMaker(savedMaker);
        var saved2 = carService.save(car2);


        var foundMaker = makerService.findByName("BMW");
        System.out.println(foundMaker.getCars().size());
    }
}
