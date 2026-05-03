package com.devconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DevconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevconnectApplication.class, args);
	}

}
