package com.cts.sbid;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootIocDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIocDemoApplication.class, args);
	}

	@Bean
	public LocalDate today() {
		return LocalDate.now();
	}
	
	@Bean
	public Scanner scan() {
		return new Scanner(System.in);
	}
}
