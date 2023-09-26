package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.LevelHistoryRepository;
import com.kenzie.appserver.repositories.ShipInformationRepository;
import com.kenzie.appserver.repositories.model.LevelHistoryRecord;
import com.kenzie.appserver.service.model.LevelHistory;

import java.util.ArrayList;
import java.util.List;

public class LevelHistoryService {
    private LevelHistoryRepository levelHistoryRepository;


    // TODO Constructors
    public LevelHistoryService(LevelHistoryRepository levelHistoryRepository) {
        this.levelHistoryRepository = levelHistoryRepository;
    }


    // TODO Methods
    public LevelHistory addLevelToHistory(LevelHistory levelHistory) {
        LevelHistoryRecord levelHistoryRecord = new LevelHistoryRecord();

        levelHistoryRecord.setGameId(levelHistory.getGameId());
        levelHistoryRecord.setLevelNumber(levelHistory.getLevelNumber());
        levelHistoryRecord.setCompletionTime(levelHistory.getCompletionTime());

        levelHistoryRepository.save(levelHistoryRecord);

        return levelHistory;
    }

    public List<LevelHistory> getAllLevelHistory() {
        List<LevelHistory> levelHistory = new ArrayList<>();

        Iterable<LevelHistoryRecord> levelHistoryIterator = levelHistoryRepository.findAll();

        for(LevelHistoryRecord record : levelHistoryIterator) {
            levelHistory.add(new LevelHistory(record.getGameId(),
                    record.getLevelNumber(),
                    record.getCompletionTime()));
        }

        return levelHistory;
    }
}
