package com.wusw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AccreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccreditApplication.class, args);
	}
}
