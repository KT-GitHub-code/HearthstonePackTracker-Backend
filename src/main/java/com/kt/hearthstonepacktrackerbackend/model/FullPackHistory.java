package com.kt.hearthstonepacktrackerbackend.model;

import java.util.HashMap;
import java.util.Map;

public class FullPackHistory {

    private final Map<PackType,PackHistory> packHistories;

    public Map<PackType, PackHistory> getPackHistories() {
        return packHistories;
    }

    public FullPackHistory() {
        this.packHistories = new HashMap<>();
    }
}
