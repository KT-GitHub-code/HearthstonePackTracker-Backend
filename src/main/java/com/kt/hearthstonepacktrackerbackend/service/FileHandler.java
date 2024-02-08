package com.kt.hearthstonepacktrackerbackend.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileHandler {

    private static final String FILE_PATH = "FullPackHistory.json";
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public FullPackHistory readFromFile() {

        Path filePath = Paths.get(FILE_PATH);
        try {
            if (Files.exists(filePath)) {
                String content = Files.readString(filePath);
                return objectMapper.readValue(content, FullPackHistory.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FullPackHistory(); // Default value if file doesn't exist or there is an error reading it
    }

    public void writeToFile(FullPackHistory fullPackHistory) {

        Path filePath = Paths.get(FILE_PATH);
        try {
            String content = objectMapper.writeValueAsString(fullPackHistory);
            Files.writeString(filePath, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

