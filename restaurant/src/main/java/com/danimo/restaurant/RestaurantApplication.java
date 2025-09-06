package com.danimo.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean("ClientRestApi")
	public RestClient restClient() {
		return RestClient.builder()
				.baseUrl("http://localhost:8000/v1/clients/check/")
				.build();
	}
	@Bean("UserRestApi")
	public RestClient restUserClient() {
		return RestClient.builder()
				.baseUrl("http://localhost:8000/v1/users/check/")
				.build();
	}
	@Bean("LocationRestApi")
	public RestClient restLocationClient() {
		return RestClient.builder()
				.baseUrl("http://localhost:8000/v1/locations/check/")
				.build();
	}


}
