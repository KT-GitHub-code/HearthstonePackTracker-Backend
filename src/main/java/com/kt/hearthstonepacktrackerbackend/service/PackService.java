package com.kt.hearthstonepacktrackerbackend.service;

import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PackService {

    private final FileHandler fileHandler;

    public PackService(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    private FullPackHistory readFullPackHistoryFromFile() {
        return fileHandler.readFromFile();
    }

    private void writeFullPackHistoryToFile(FullPackHistory fullPackHistory) {
        fileHandler.writeToFile(fullPackHistory);
    }

    public FullPackHistory getFullPackHistory() {
        return readFullPackHistoryFromFile();
    }

    public PackHistory getPackHistory(PackType packType) {
        FullPackHistory fullPackHistory = readFullPackHistoryFromFile();
        return fullPackHistory.getPackHistories().get(packType);
    }

    public void openPackWithLegendary(PackType packType) {

        FullPackHistory fullPackHistory = readFullPackHistoryFromFile();

        PackHistory packHistory = fullPackHistory.getPackHistories().get(packType);

        ArrayList<Integer> runs = (ArrayList<Integer>) packHistory.getRuns();

        packHistory.setCurrentCount(packHistory.getCurrentCount() + 1);

        runs.add(packHistory.getCurrentCount());

        packHistory.setCurrentCount(0);

        packHistory.setRuns(runs);

        fullPackHistory.getPackHistories().put(packType, packHistory);

        writeFullPackHistoryToFile(fullPackHistory);
    }

    public void openPackWithoutLegendary(PackType packType) {

        FullPackHistory fullPackHistory = readFullPackHistoryFromFile();

        PackHistory packHistory = fullPackHistory.getPackHistories().get(packType);

        packHistory.setCurrentCount(packHistory.getCurrentCount() + 1);

        fullPackHistory.getPackHistories().put(packType, packHistory);

        writeFullPackHistoryToFile(fullPackHistory);
    }
}
