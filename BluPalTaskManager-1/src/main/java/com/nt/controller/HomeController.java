package com.nt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	
	@GetMapping("/tasks")
	public ResponseEntity<String> getAssignedsUsersTask()
			 {
		
		return new ResponseEntity<>("welcome to task service", HttpStatus.OK);

	}

}
