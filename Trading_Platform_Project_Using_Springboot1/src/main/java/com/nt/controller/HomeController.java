package com.nt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping()
	public String grearController() {
		return "welcome to Trading platform";
	}
	
	@GetMapping("/api")
	public String secure() {
		return "welcome to Trading platform secure";
	}

}
