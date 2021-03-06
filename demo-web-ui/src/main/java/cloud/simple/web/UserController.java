/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package cloud.simple.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cloud.simple.model.User;
import cloud.simple.service.BarClient;
import cloud.simple.service.UserServiceClient;



@RestController
public class UserController {
		
	@Autowired
	UserServiceClient userService;

	@Autowired
	BarClient fooClient;
	
	@RequestMapping(value="/users")
	public ResponseEntity<List<User>> readUserInfo(){
		List<User> users=userService.sayHello();		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}

	@RequestMapping(value="/go")
	public ResponseEntity<String> fooGo(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b){
		return new ResponseEntity<String>(fooClient.go(a, b), HttpStatus.OK);
	}
}
