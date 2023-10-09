package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.LevelHistoryRepository;
import com.kenzie.appserver.repositories.model.LevelHistoryRecord;
import com.kenzie.appserver.service.model.LevelHistory;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LevelHistoryServiceTest {
    private LevelHistoryRepository levelHistoryRepository;
    private LevelHistoryService levelHistoryService;

    @BeforeEach
    void setup() {
        levelHistoryRepository = mock(LevelHistoryRepository.class);
        levelHistoryService = new LevelHistoryService(levelHistoryRepository);
    }

    @Test
    void addLevelToHistory_validHistory_savesSuccessfully() {
        // GIVEN
        LevelHistory levelHistory = new LevelHistory("gameId", 1, 1);
        ArgumentCaptor<LevelHistoryRecord> levelHistoryCaptor = ArgumentCaptor.forClass(LevelHistoryRecord.class);

        // WHEN
        LevelHistory returnedLevelHistory = levelHistoryService.addLevelToHistory(levelHistory);
        Assertions.assertNotNull(returnedLevelHistory);

        verify(levelHistoryRepository).save(levelHistoryCaptor.capture());
        LevelHistoryRecord record = levelHistoryCaptor.getValue();

        // THEN
        assertEquals(levelHistory.getGameId(), record.getGameId());
        assertEquals(levelHistory.getLevelNumber(), record.getLevelNumber());
        assertEquals(levelHistory.getCompletionTime(), record.getCompletionTime());
    }

    @Test
    void getAllLevelHistory_returnsListOfLevelHistory() {
        // GIVEN
        LevelHistoryRecord record = new LevelHistoryRecord();
        record.setGameId("gameId");
        record.setLevelNumber(1);
        record.setCompletionTime(1);

        LevelHistoryRecord record2 = new LevelHistoryRecord();
        record2.setGameId("gameId2");
        record2.setLevelNumber(2);
        record2.setCompletionTime(2);

        List<LevelHistoryRecord> recordList = new ArrayList<>();
        recordList.add(record);
        recordList.add(record2);
        when(levelHistoryRepository.findAll()).thenReturn(recordList);

        // WHEN
        List<LevelHistory> levelHistoryList = levelHistoryService.getAllLevelHistory();

        // THEN
        Assertions.assertNotNull(levelHistoryList, "The levelHistory list is returned");
        Assertions.assertEquals(2, levelHistoryList.size(), "There are two levelHistory records");

        assertEquals(record.getGameId(), levelHistoryList.get(0).getGameId());
        assertEquals(record.getLevelNumber(), levelHistoryList.get(0).getLevelNumber());
        assertEquals(record.getCompletionTime(), levelHistoryList.get(0).getCompletionTime());

        assertEquals(record2.getGameId(), levelHistoryList.get(1).getGameId());
        assertEquals(record2.getLevelNumber(), levelHistoryList.get(1).getLevelNumber());
        assertEquals(record2.getCompletionTime(), levelHistoryList.get(1).getCompletionTime());
    }


}
