package com.manonssharedkitchen.cloud_hours;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping("/brivologs")
    public String handleIncomingBrivoLogs(@RequestBody String requestBody) {
        // Process the request body here
        return "POST request received: " + requestBody;
    }

}