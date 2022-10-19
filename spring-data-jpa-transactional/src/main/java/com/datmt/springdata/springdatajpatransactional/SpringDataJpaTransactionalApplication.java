package com.datmt.springdata.springdatajpatransactional;


import com.datmt.springdata.springdatajpatransactional.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDataJpaTransactionalApplication {

    public static void main(String[] args) {

        var appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        var datasourceBean = appContext.getBean("default_datasource");


    }

}
