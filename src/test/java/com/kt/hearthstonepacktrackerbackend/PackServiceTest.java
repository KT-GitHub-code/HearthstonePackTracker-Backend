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

    // openPackWithoutLegendary writes the updated pack history to file
    @Test
    public void test_write_to_file() {
        // Arrange
        FileHandler fileHandler = mock(FileHandler.class);
        FullPackHistory fullPackHistory = new FullPackHistory();
        PackType packType = PackType.Classic;
        PackHistory packHistory = new PackHistory(packType, 0, new ArrayList<>());
        fullPackHistory.getPackHistories().put(packType, packHistory);
        when(fileHandler.readFromFile()).thenReturn(fullPackHistory);
        PackService packService = new PackService(fileHandler);

        // Act
        packService.openPackWithoutLegendary(packType);

        // Assert
        verify(fileHandler, times(1)).writeToFile(fullPackHistory);
    }

    // openPackWithoutLegendary returns the updated pack history
    @Test
    public void test_return_updated_pack_history() {
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
        assertEquals(packHistory, result);
    }

    // openPackWithoutLegendary does not increment the current count of the pack history if the current count is 39
    @Test
    public void test_does_not_increment_current_count() {
        // Arrange
        FileHandler fileHandler = mock(FileHandler.class);
        FullPackHistory fullPackHistory = new FullPackHistory();
        PackType packType = PackType.Classic;
        PackHistory packHistory = new PackHistory(packType, 39, new ArrayList<>());
        fullPackHistory.getPackHistories().put(packType, packHistory);
        when(fileHandler.readFromFile()).thenReturn(fullPackHistory);
        PackService packService = new PackService(fileHandler);

        // Act
        PackHistory result = packService.openPackWithoutLegendary(packType);

        // Assert
        assertEquals(39, result.getCurrentCount());
    }

    // openPackWithoutLegendary returns the pack history without updating it if the current count is 39
    @Test
    public void test_return_pack_history_without_update() {
        // Arrange
        FileHandler fileHandler = mock(FileHandler.class);
        FullPackHistory fullPackHistory = new FullPackHistory();
        PackType packType = PackType.Classic;
        PackHistory packHistory = new PackHistory(packType, 39, new ArrayList<>());
        fullPackHistory.getPackHistories().put(packType, packHistory);
        when(fileHandler.readFromFile()).thenReturn(fullPackHistory);
        PackService packService = new PackService(fileHandler);

        // Act
        PackHistory result = packService.openPackWithoutLegendary(packType);

        // Assert
        assertEquals(packHistory, result);
    }

    // openPackWithoutLegendary throws an exception if the pack type is not found in the full pack history
    @Test
    public void test_throw_exception_if_pack_type_not_found() {
        // Arrange
        FileHandler fileHandler = mock(FileHandler.class);
        FullPackHistory fullPackHistory = new FullPackHistory();
        PackType packType = PackType.Classic;
        when(fileHandler.readFromFile()).thenReturn(fullPackHistory);
        PackService packService = new PackService(fileHandler);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            packService.openPackWithoutLegendary(packType);
        });
    }

}
