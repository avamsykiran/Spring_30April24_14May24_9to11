package com.cts.springiocdemo;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@Configurable
@ComponentScan("com.cts.springiocdemo")
@PropertySource("classpath:application.properties")
public class MyBeanConfig {

	@Bean
	public LocalDate today() {
		return LocalDate.now();
	}
	
	@Bean
	public Scanner scan() {
		return new Scanner(System.in);
	}
}
