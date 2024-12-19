package com.manonssharedkitchen.cloudhours;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import com.manonssharedkitchen.cloudhours.entity.AccessEvent;
import com.manonssharedkitchen.cloudhours.model.Record;
import com.manonssharedkitchen.cloudhours.service.AccessEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class HelloController {
	@Autowired AccessEventService accessEventService;

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping("/brivologs")
	public String handleIncomingBrivoLogs(@RequestBody JsonNode payload) {
		JsonNode attachments = payload.path("attachments");

		if (attachments.isArray()) {

			for (JsonNode attachment : attachments) {
				String base64Content = attachment.path("content").asText();
				byte[] decodedBytes = Base64.getDecoder().decode(base64Content);
				String decodedContent = new String(decodedBytes, StandardCharsets.UTF_8);

				try {
					List<Record> records = CsvParser.parseBrivoReport(decodedContent);
					for (Record record : records) {
						accessEventService.saveAccessEvent(record);
					}
					List<AccessEvent> accessEvents = accessEventService.getAllAccessEvents();
					System.out.println(String.format("Found %d events in database", accessEvents.size()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Process the request body here
		return "POST request received: " + payload;
	}

}