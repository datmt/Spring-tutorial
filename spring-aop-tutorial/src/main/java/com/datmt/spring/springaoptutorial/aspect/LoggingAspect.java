package com.datmt.spring.springaoptutorial.aspect;

import com.datmt.spring.springaoptutorial.transformer.Transformer;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class LoggingAspect {

    private final Logger logger = org.apache.logging.log4j.LogManager.getLogger(LoggingAspect.class);

    @Before("@annotation(com.datmt.spring.springaoptutorial.aspect.BeforeLogging)")
    public void logBefore(JoinPoint joinPoint) {
        //get args
        Object[] args = joinPoint.getArgs();
        var signature = (MethodSignature) joinPoint.getSignature();
        //get annotation
        var beforeLogging = signature.getMethod().getAnnotation(BeforeLogging.class);

        //get transformer
        var transformers = beforeLogging.value();

        //create the list of transformers
        var transformerList = getTransformers(transformers);

        List<Object> transformedObjects = transformObject(transformerList, args);

        var invokeClass = joinPoint.getTarget().getClass();
        //create the logger for the class
        Logger logger = org.apache.logging.log4j.LogManager.getLogger(invokeClass);
        //log
        logger.info("Before logging {}", transformedObjects);
        transformedObjects.clear();
        transformerList.clear();
    }

    @AfterReturning(value = "@annotation(com.datmt.spring.springaoptutorial.aspect.AfterLogging)", returning = "returnValue")
    public void logAfter(JoinPoint joinPoint, Object returnValue) {
        var signature = (MethodSignature) joinPoint.getSignature();
        //get annotation
        var afterLogging = signature.getMethod().getAnnotation(AfterLogging.class);

        //get transformer
        var transformers = afterLogging.value();

        //create the list of transformers
        var transformerList = getTransformers(transformers);

        //transform the return value
        try {
            for (Transformer<Object> transformer : transformerList) {
                returnValue = transformer.transform(returnValue);
            }
            logger.info("After logging: {}", returnValue);
        } catch (Exception e) {
            logger.debug("Error while transforming object due to class cast");
        }
    }

    private List<Object> transformObject(ArrayList<Transformer<Object>> transformerList, Object[] args) {
        List<Object> transformedObjects = new ArrayList<>();
        //transform args
        for (Object arg : args) {
            //get the type of the object and the type of the transformer, if they match, transform
            try {
                for (Transformer<Object> transformer : transformerList) {
                    arg = transformer.transform(arg);
                }
            } catch (Exception e) {
                logger.debug("Error while transforming object due to class cast");
            }
            transformedObjects.add(arg);
        }

        return transformedObjects;
    }

    private ArrayList<Transformer<Object>> getTransformers(Class<? extends Transformer>[] transformers) {
        var transformerList = new ArrayList<Transformer<Object>>();
        for (var transformer : transformers) {
            try {
                transformerList.add(transformer.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                logger.debug("Error while creating transformer");
            }
        }
        return transformerList;
    }
}
