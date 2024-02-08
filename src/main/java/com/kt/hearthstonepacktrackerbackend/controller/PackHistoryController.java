package com.kt.hearthstonepacktrackerbackend.controller;

import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackType;
import com.kt.hearthstonepacktrackerbackend.service.PackService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackHistoryController {

    public PackService packService;

    public PackHistoryController(PackService packService) {
        this.packService = packService;
    }

    public FullPackHistory getFullPackHistory() {
        return packService.getFullPackHistory();
    }

    public PackHistory getPackHistory(PackType packType) {
        return packService.getPackHistory(packType);
    }

}
