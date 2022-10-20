package com.datmt.springdata.springdatajpatransactional;


import com.datmt.springdata.springdatajpatransactional.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

public class SpringDataJpaTransactionalApplication {

    public static void main(String[] args) {

        var appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        var bankTransferService = appContext.getBean(BankTransferService.class);
        bankTransferService.resetBalance();

        bankTransferService.sendAndSplitToOtherAccounts("Alice", "Bob", 100L, "Bob saving", "Bob investing");
    }


}
