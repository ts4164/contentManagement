package com.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controllers {

	@Autowired
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	
	@Autowired
	@RequestMapping(value="/adminProfile")
	public String adminProfile(){
		return "adminProfile";
	}
	
	@Autowired
	@RequestMapping(value="/userProfile")
	public String user(){
		return "userProfile";
	}
	
}
