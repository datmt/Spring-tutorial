package com.datmt.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.datmt.logging.models.Order;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication

@Slf4j
public class LoggingTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingTutorialApplication.class, args);
	}

	@Slf4j
	@Component
	public static class TestLogService {
		public TestLogService() {
			while (true) {
				var order = new Order();
				order.setOrderId("123");
				order.setCustomerName("John");
				order.setOrderDate("2020-01-01");
				log.info("{}", order);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
