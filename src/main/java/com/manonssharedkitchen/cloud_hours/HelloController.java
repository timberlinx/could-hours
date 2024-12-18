package com.manonssharedkitchen.cloud_hours;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping("/brivologs")
    public String handleIncomingBrivoLogs(@RequestBody JsonNode payload) {
        // Process the request body here
        return "POST request received: " + payload;
    }

}