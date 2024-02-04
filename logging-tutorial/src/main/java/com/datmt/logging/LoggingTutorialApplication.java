package com.datmt.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

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
				log.trace("Hello World");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
