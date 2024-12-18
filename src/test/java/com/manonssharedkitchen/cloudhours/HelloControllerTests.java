package com.manonssharedkitchen.cloudhours;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class HelloControllerTest {

	@Test
	void testCsvParsing() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String filePath = "C:\\Users\\timbe\\VS Code\\could-hours\\src\\test\\java\\com\\manonssharedkitchen\\cloudhours\\data\\brivologs";
        String content = Files.readString(Paths.get(filePath));
		JsonNode node = mapper.readTree(content);
		new HelloController().handleIncomingBrivoLogs(node);
	}

}
