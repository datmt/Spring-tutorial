package com.datmt.spring.springaoptutorial.service;

import com.datmt.spring.springaoptutorial.aspect.AfterLogging;
import com.datmt.spring.springaoptutorial.aspect.BeforeLogging;
import com.datmt.spring.springaoptutorial.model.Dog;
import com.datmt.spring.springaoptutorial.model.SuperDog;
import com.datmt.spring.springaoptutorial.transformer.HidePhoneTransformer;
import com.datmt.spring.springaoptutorial.transformer.HidePowerTransformer;
import com.datmt.spring.springaoptutorial.transformer.SuperDogHidePhoneTransformer;
import org.springframework.stereotype.Service;

@Service
public class BuffDogService {

    @BeforeLogging({HidePhoneTransformer.class, HidePowerTransformer.class})
    @AfterLogging({SuperDogHidePhoneTransformer.class})
    public SuperDog buffDog(Dog dog) {
        return new SuperDog(dog.name(), dog.phoneNumber(), dog.age(), dog.hp() + 100);
    }

    @BeforeLogging({HidePhoneTransformer.class, HidePowerTransformer.class})
    public Dog retireDog(SuperDog dog) {
//        return new Dog(dog.name(), dog.phoneNumber(), dog.age(), dog.hp() - 100);
        return null;
    }

    @BeforeLogging
    public void listDog(Dog... dogs) {

    }
}
