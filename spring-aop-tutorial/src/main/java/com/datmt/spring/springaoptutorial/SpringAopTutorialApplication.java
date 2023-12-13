package com.datmt.spring.springaoptutorial;

import com.datmt.spring.springaoptutorial.model.Dog;
import com.datmt.spring.springaoptutorial.model.SuperDog;
import com.datmt.spring.springaoptutorial.service.BuffDogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAopTutorialApplication implements CommandLineRunner {

    @Autowired
    private BuffDogService buffDogService;

    private final Logger logger = LoggerFactory.getLogger(SpringAopTutorialApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringAopTutorialApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Dog dog = new Dog("Super Dog", "0192392323", 1, 100);
        var superDog = buffDogService.buffDog(dog);

        logger.info("Super dog: {}", superDog);
        var normalDog = buffDogService.retireDog(superDog);

        buffDogService.listDog(dog, dog, dog);
    }
}
