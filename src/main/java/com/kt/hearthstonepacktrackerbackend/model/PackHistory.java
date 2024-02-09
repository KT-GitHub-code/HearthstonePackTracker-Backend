package com.kt.hearthstonepacktrackerbackend.model;

import java.util.List;

public class PackHistory {

    private final PackType packType;

    private int currentCount;


    private List<Integer> runs;

    public PackHistory(PackType packType, int currentCount,  List<Integer> runs) {
        this.packType = packType;
        this.currentCount = currentCount;
        this.runs = runs;
    }

    public PackType getPackType() {
        return packType;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public List<Integer> getRuns() {
        return runs;
    }

    public void setRuns(List<Integer> runs) {
        this.runs = runs;
    }
}
