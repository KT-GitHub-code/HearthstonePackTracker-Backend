package com.kt.hearthstonepacktrackerbackend.controller;

import com.kt.hearthstonepacktrackerbackend.model.PackType;
import com.kt.hearthstonepacktrackerbackend.service.PlotService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlotController {

    public PlotService plotService;

    public PlotController(PlotService plotService) {
        this.plotService = plotService;
    }

    @GetMapping("/api/plot/{packType}")
    public ResponseEntity<Resource> getPlottedImage(@PathVariable PackType packType) {
        return plotService.getPlottedImage(packType);
    }

}
