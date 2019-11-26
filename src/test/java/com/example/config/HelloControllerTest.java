package com.example.config;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void welecome_return200() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk())
				.andExpect(content().string("Welcome"));
	}

}
