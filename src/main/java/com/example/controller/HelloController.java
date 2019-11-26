package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public ResponseEntity<String> getWelcomePage() {

		return new ResponseEntity<String>("Welcome", HttpStatus.OK);
	}

	@GetMapping("/getUser/{input}")
	public ResponseEntity<String> getUser(@PathVariable String input) {

		return new ResponseEntity<String>(input, HttpStatus.OK);
	}

}
