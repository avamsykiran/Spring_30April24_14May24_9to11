package com.cts.springiocdemo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetServiceSimpleImpl implements GreetService {

	@Override
	public String greet(String userName) {
		return "Heelo " + userName;
	}

}
