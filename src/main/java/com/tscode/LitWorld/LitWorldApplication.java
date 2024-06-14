package com.tscode.LitWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class LitWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(LitWorldApplication.class, args);
	}

}
