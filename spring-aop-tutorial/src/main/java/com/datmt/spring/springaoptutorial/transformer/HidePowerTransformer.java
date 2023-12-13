package com.datmt.spring.springaoptutorial.transformer;

import com.datmt.spring.springaoptutorial.model.Dog;

public class HidePowerTransformer implements Transformer<Dog> {
    @Override
    public Dog transform(Dog dog) {
        return new Dog(dog.name(), dog.phoneNumber(), dog.age(), 0);
    }
}
