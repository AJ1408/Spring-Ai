package com.ashish.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiProjectApplication.class, args);
			System.out.println("Credential Path: " + System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
	}

}
