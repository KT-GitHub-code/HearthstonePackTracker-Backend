package com.kt.hearthstonepacktrackerbackend.controller;

import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackType;
import com.kt.hearthstonepacktrackerbackend.service.PackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackHistoryController {

    public PackService packService;

    public PackHistoryController(PackService packService) {
        this.packService = packService;
    }

    @GetMapping("/api/fullpackhistory")
    public FullPackHistory getFullPackHistory() {
        return packService.getFullPackHistory();
    }

    @GetMapping("/api/packhistory/{packType}")
    public PackHistory getPackHistory(@PathVariable PackType packType) {
        return packService.getPackHistory(packType);
    }

    @PostMapping("/api/openpackwithlegendary/{packType}")
    public PackHistory openPackWithLegendary(@PathVariable PackType packType) {
        return packService.openPackWithLegendary(packType);
    }

    @PostMapping("/api/openpackwithoutlegendary/{packType}")
    public PackHistory openPackWithoutLegendary(@PathVariable PackType packType) {
        return packService.openPackWithoutLegendary(packType);
    }

}
