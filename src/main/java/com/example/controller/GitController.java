package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GitController {

	@PostMapping("/getSwaggerFile")
	public String getSwaggerFile(MultipartFile file) {

		return "done";
	}

}
