package com.kt.hearthstonepacktrackerbackend.service;

import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackType;
import org.springframework.stereotype.Service;

@Service
public class PackService {

    private final FileHandler fileHandler;

    public PackService(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public FullPackHistory getFullPackHistory() {
        return readFullPackHistoryFromFile();
    }

    public PackHistory getPackHistory(PackType packType) {
        FullPackHistory fullPackHistory = readFullPackHistoryFromFile();
        return fullPackHistory.getPackHistories().get(packType);
    }

    private FullPackHistory readFullPackHistoryFromFile() {
        return fileHandler.readFromFile();
    }

}
