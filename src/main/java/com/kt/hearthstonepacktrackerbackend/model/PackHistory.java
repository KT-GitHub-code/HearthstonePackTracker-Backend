package com.kt.hearthstonepacktrackerbackend.model;

import java.util.List;

public class PackHistory {

    public final PackType packType;

    public int currentCount;

    public Boolean isCurrentCounterOngoing;

    public List<Integer> openings;

    public PackHistory(PackType packType, int currentCount, Boolean isCurrentCounterOngoing, List<Integer> openings) {
        this.packType = packType;
        this.currentCount = currentCount;
        this.isCurrentCounterOngoing = isCurrentCounterOngoing;
        this.openings = openings;
    }
}
