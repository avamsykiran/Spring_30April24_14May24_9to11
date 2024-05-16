package com.cts.springiocdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cts.springiocdemo.ui.HomeScreen;

public class SpringIoCDemoApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfig.class);
		
		HomeScreen hs = (HomeScreen) context.getBean("homeScreen");
		hs.run();
		
	}

}
