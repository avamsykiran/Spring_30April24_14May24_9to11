package com.cts.springiocdemo.ui;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cts.springiocdemo.service.CounterService;
import com.cts.springiocdemo.service.GreetService;

@Component
public class HomeScreen {
	
	@Value("${app.name}")
	private String appName;
	
	@Autowired
	private LocalDate date;
	
	@Autowired
	private Scanner kbin;
	
	@Autowired
	@Qualifier("greetServiceSimpleImpl")
	private GreetService greetService1;

	@Autowired
	@Qualifier("greetServiceTimeBasedImpl")
	private GreetService greetService2;

	@Autowired
	private CounterService counter1;

	@Autowired
	private CounterService counter2;
	
	@Autowired
	private CounterService counter3;
	
	public void run() {
		System.out.println("Today: "+date);
		System.out.println(appName);
		System.out.println("-----------------------------------------------");
		
		System.out.println("User Name? ");
		String userName = kbin.next();
		System.out.println(greetService1.greet(userName));
		System.out.println(greetService2.greet(userName));
		
		System.out.println(counter1.next());
		System.out.println(counter1.next());
		System.out.println(counter2.next());
		System.out.println(counter2.next());
		System.out.println(counter3.next());
		System.out.println(counter3.next());
		
	}
}
