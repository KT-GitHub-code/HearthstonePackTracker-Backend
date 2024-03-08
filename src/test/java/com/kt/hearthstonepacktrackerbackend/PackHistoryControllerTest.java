package com.kt.hearthstonepacktrackerbackend;

import com.kt.hearthstonepacktrackerbackend.controller.PackHistoryController;

import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackType;
import com.kt.hearthstonepacktrackerbackend.service.PackService;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PackHistoryControllerTest {

    // GET request to /api/fullpackhistory returns FullPackHistory object
    @Test
    public void test_getFullPackHistory_returnsFullPackHistory() {
        // Arrange
        PackService packService = mock(PackService.class);
        FullPackHistory expected = new FullPackHistory();
        when(packService.getFullPackHistory()).thenReturn(expected);
        PackHistoryController packHistoryController = new PackHistoryController(packService);

        // Act
        FullPackHistory actual = packHistoryController.getFullPackHistory();

        // Assert
        assertEquals(expected, actual);
    }

    // GET request to /api/packhistory/{packType} returns PackHistory object for specified packType
    @Test
    public void test_getPackHistory_returnsPackHistoryForSpecifiedPackType() {
        // Arrange
        PackService packService = mock(PackService.class);
        PackType packType = PackType.Classic;
        PackHistory expected = new PackHistory(packType);
        when(packService.getPackHistory(packType)).thenReturn(expected);
        PackHistoryController packHistoryController = new PackHistoryController(packService);

        // Act
        PackHistory actual = packHistoryController.getPackHistory(packType);

        // Assert
        assertEquals(expected, actual);
    }

}
