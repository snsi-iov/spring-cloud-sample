/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */
package cloud.simple.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cloud.simple.model.User;

@Service
public class UserServiceClient {
	 @Autowired	 
	 RestTemplate restTemplate;
	

	@HystrixCommand(fallbackMethod = "fallbackHello")
	public List<User> sayHello() {
		List<User> users = new ArrayList<User>();
		String name = restTemplate.getForObject("http://BAR/?q=info.component", String.class);
		User u = new User(); u.setUsername(name);
		users.add(u);
        System.out.println("response should be good");
		return users;
	}
	

	int count = 0;
	@HystrixCommand(fallbackMethod = "fallbackHello")
	public List<User> fallbackHello() {
		count++;
        System.out.println("HystrixCommand fallbackHello handle! "+count);
		List<User> users = new ArrayList<User>();
		String name = restTemplate.getForObject("http://BAR/?q=info.component", String.class);
		User u = new User(); u.setUsername(name);
		users.add(u);
		System.out.println("response should be good -----");
		return users;
	}
	 
//	 @Autowired
//	 FeignUserService feignUserService;
	 
}
