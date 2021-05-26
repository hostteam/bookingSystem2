package com.example.bookingSystem2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.bookingSystem2.repository")
@SpringBootApplication
public class BookingSystem2Application {

	public static void main(String[] args) {

		SpringApplication.run(BookingSystem2Application.class, args);

	}

}
