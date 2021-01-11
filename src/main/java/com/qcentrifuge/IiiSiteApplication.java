package com.qcentrifuge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class IiiSiteApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(IiiSiteApplication.class, args);
	}

}