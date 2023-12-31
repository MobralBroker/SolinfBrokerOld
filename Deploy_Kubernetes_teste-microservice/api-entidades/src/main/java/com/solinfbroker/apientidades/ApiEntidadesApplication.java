package com.solinfbroker.apientidades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiEntidadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEntidadesApplication.class, args);
	}

}
