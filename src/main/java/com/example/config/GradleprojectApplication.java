package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.*")
public class GradleprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradleprojectApplication.class, args);
	}

}
