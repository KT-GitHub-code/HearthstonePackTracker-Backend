package com.kt.hearthstonepacktrackerbackend.service;

import com.kt.hearthstonepacktrackerbackend.model.FullPackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackHistory;
import com.kt.hearthstonepacktrackerbackend.model.PackType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class FullPackHistoryBuilder {


    public FullPackHistory buildInitialFullPackHistory() {

        FullPackHistory  fullPackHistory = new FullPackHistory();

        List<PackType> packTypes = Arrays.asList(PackType.values());

        packTypes.forEach(packType ->
            fullPackHistory.getPackHistories().put(
                    packType,
                    new PackHistory(
                            packType,
                            0,
                            new LinkedList<>())
            )
        );

        return fullPackHistory;
    }
}
