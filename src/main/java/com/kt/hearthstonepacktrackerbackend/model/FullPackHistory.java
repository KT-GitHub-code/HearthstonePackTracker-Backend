package com.kt.hearthstonepacktrackerbackend.model;

import java.util.Map;

public class FullPackHistory {

    private Map<PackType,PackHistory> packHistories;

    public Map<PackType, PackHistory> getPackHistories() {
        return packHistories;
    }
}
