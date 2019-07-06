package com.exlibris;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ExlibrisApplication {

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(ExlibrisApplication.class, args);
	}

}
