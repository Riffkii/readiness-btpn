package com.egg.iot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EggIotApplication {

	public static void main(String[] args) {
		SpringApplication.run(EggIotApplication.class, args);
	}

}
