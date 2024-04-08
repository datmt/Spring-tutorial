package com.datmt.spring.springaoptutorial.model;

public class SuperDog {
    String name;
    String phoneNumber;
    int age;
    int hp;

    public SuperDog(String name, String phoneNumber, int age, int hp) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.hp = hp;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SuperDog{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", hp=" + hp +
                '}';
    }
}
