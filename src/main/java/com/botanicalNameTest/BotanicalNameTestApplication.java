package com.botanicalNameTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:private.properties")
public class BotanicalNameTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotanicalNameTestApplication.class, args);
	}

}
