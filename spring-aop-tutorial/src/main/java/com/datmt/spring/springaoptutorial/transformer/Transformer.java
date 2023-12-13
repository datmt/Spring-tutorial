package com.datmt.spring.springaoptutorial.transformer;

@FunctionalInterface
public interface Transformer<T> {
    T transform(T t);
}
