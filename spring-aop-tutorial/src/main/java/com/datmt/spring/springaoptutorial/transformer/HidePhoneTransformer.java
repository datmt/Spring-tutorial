package com.datmt.spring.springaoptutorial.transformer;

import com.datmt.spring.springaoptutorial.model.Dog;

public class HidePhoneTransformer implements Transformer<Dog> {
    @Override
    public Dog transform(Dog dog) {
        return new Dog(dog.name(), "**********", dog.age(), dog.hp());
    }
}
