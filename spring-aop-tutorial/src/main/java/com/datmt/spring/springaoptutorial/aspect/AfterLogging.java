package com.datmt.spring.springaoptutorial.aspect;

import com.datmt.spring.springaoptutorial.transformer.Transformer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface AfterLogging {
    Class<? extends Transformer>[] value() default {};
}
