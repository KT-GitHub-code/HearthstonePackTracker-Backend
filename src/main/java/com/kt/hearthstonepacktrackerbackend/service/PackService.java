package com.kt.hearthstonepacktrackerbackend.service;

import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PackService {

    private final FileHandler fileHandler;

    private static final Logger logger = LoggerFactory.getLogger(FileHandler.class);


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

    public PackHistory openPackWithLegendary(PackType packType) {

        FullPackHistory fullPackHistory = readFullPackHistoryFromFile();

        PackHistory packHistory = fullPackHistory.getPackHistories().get(packType);

        ArrayList<Integer> runs = (ArrayList<Integer>) packHistory.getRuns();

        packHistory.setCurrentCount(packHistory.getCurrentCount() + 1);

        runs.add(packHistory.getCurrentCount());

        packHistory.setCurrentCount(0);

        packHistory.setRuns(runs);

        fullPackHistory.getPackHistories().put(packType, packHistory);

        writeFullPackHistoryToFile(fullPackHistory);

        logger.info("Pack with legendary opened: " + packType);

        return packHistory;
    }

    public PackHistory openPackWithoutLegendary(PackType packType) {

        FullPackHistory fullPackHistory = readFullPackHistoryFromFile();

        PackHistory packHistory = fullPackHistory.getPackHistories().get(packType);

        // bad luck protection: opening #40 must have a legendary card
        if(packHistory.getCurrentCount() == 39){
            return packHistory;
        }

        packHistory.setCurrentCount(packHistory.getCurrentCount() + 1);

        fullPackHistory.getPackHistories().put(packType, packHistory);

        writeFullPackHistoryToFile(fullPackHistory);

        logger.info("Pack without legendary opened: " + packType);

        return packHistory;
    }
}
