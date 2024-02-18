package com.kt.hearthstonepacktrackerbackend;

import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackType;
import com.kt.hearthstonepacktrackerbackend.service.FileHandler;
import com.kt.hearthstonepacktrackerbackend.service.PackService;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PackServiceTest {


    // getFullPackHistory returns FullPackHistory object
    @Test
    public void test_get_full_pack_history() {
        // Arrange
        FileHandler fileHandler = mock(FileHandler.class);
        FullPackHistory fullPackHistory = new FullPackHistory();
        when(fileHandler.readFromFile()).thenReturn(fullPackHistory);
        PackService packService = new PackService(fileHandler);

        // Act
        FullPackHistory result = packService.getFullPackHistory();

        // Assert
        assertEquals(fullPackHistory, result);
    }

    // getPackHistory returns PackHistory object for given PackType
    @Test
    public void test_get_pack_history() {
        // Arrange
        FileHandler fileHandler = mock(FileHandler.class);
        FullPackHistory fullPackHistory = new FullPackHistory();
        PackType packType = PackType.Classic;
        PackHistory expected = new PackHistory(packType);
        fullPackHistory.getPackHistories().put(packType, expected);
        when(fileHandler.readFromFile()).thenReturn(fullPackHistory);
        PackService packService = new PackService(fileHandler);

        // Act
        PackHistory result = packService.getPackHistory(packType);

        // Assert
        assertEquals(expected, result);
    }

    // openPackWithLegendary increments currentCount, adds new run, and returns PackHistory object
    @Test
    public void test_open_pack_with_legendary() {
        // Arrange
        FileHandler fileHandler = mock(FileHandler.class);
        FullPackHistory fullPackHistory = new FullPackHistory();
        PackType packType = PackType.Classic;
        PackHistory packHistory = new PackHistory(packType, 0, new ArrayList<>());
        fullPackHistory.getPackHistories().put(packType, packHistory);
        when(fileHandler.readFromFile()).thenReturn(fullPackHistory);
        PackService packService = new PackService(fileHandler);

        // Act
        PackHistory result = packService.openPackWithLegendary(packType);

        // Assert
        assertEquals(1, result.getRuns().size());
        assertEquals(1, result.getRuns().get(0).intValue());
    }

    // openPackWithoutLegendary increments currentCount and returns PackHistory object
    @Test
    public void test_open_pack_without_legendary() {
        // Arrange
        FileHandler fileHandler = mock(FileHandler.class);
        FullPackHistory fullPackHistory = new FullPackHistory();
        PackType packType = PackType.Classic;
        PackHistory packHistory = new PackHistory(packType, 0, new ArrayList<>());
        fullPackHistory.getPackHistories().put(packType, packHistory);
        when(fileHandler.readFromFile()).thenReturn(fullPackHistory);
        PackService packService = new PackService(fileHandler);

        // Act
        PackHistory result = packService.openPackWithoutLegendary(packType);

        // Assert
        assertEquals(0, result.getRuns().size());
        assertEquals(1, result.getCurrentCount());
    }

}
