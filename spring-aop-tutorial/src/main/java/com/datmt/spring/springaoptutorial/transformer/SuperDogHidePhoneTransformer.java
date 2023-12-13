package com.datmt.spring.springaoptutorial.transformer;

import com.datmt.spring.springaoptutorial.model.Dog;
import com.datmt.spring.springaoptutorial.model.SuperDog;

public class SuperDogHidePhoneTransformer implements Transformer<SuperDog> {
    @Override
    public SuperDog transform(SuperDog dog) {
        return new SuperDog(dog.name(), "**********", dog.age(), dog.hp());
    }
}
