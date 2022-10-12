package com.datmt.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PackageConsumer {

    @JmsListener(destination = "package_queue", id = "package_consumer_1")
    public void packageReceiver1(String packageData) {
        System.out.printf("In receiver 1, receiving package %s%n", packageData);
    }

    @JmsListener(destination = "package_queue", id = "package_consumer_2")
    public void packageReceiver2(String packageData) {
        System.out.printf("In receiver 2, receiving package %s%n", packageData);
    }

}
