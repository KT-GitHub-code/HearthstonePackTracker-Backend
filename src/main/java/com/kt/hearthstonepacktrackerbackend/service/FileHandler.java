package com.kt.hearthstonepacktrackerbackend.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileHandler {

    private final FullPackHistoryBuilder fullPackHistoryBuilder;

    private static final Logger logger = LoggerFactory.getLogger(FileHandler.class);


    private static final String FILE_PATH = "FullPackHistory.json";
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public FileHandler(FullPackHistoryBuilder fullPackHistoryBuilder) {
        this.fullPackHistoryBuilder = fullPackHistoryBuilder;
    }

    public FullPackHistory readFromFile() {

        Path filePath = Paths.get(FILE_PATH);
        try {
            if (Files.exists(filePath)) {
                String content = Files.readString(filePath);
                logger.info("Returning FullPackHistory from file...");
                return objectMapper.readValue(content, FullPackHistory.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Building FullPackHistory...");
        FullPackHistory fullPackHistory = fullPackHistoryBuilder.buildInitialFullPackHistory();
        writeToFile(fullPackHistory);
        return readFromFile();
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

