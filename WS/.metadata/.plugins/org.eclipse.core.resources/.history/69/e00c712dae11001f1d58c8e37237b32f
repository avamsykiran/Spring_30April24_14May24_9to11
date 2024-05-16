package com.cts.springiocdemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class CounterServiceImpl implements CounterService {

	@Value("${counter.seed}")
	private int seed;
	
	@Override
	public int next() {
		return ++seed;
	}

}
