package com.datmt.spring.springcameltutorial;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.engine.DefaultProducerTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public ProducerTemplate producerTemplate(CamelContext context) {
        return new DefaultProducerTemplate(context);
    }
}
