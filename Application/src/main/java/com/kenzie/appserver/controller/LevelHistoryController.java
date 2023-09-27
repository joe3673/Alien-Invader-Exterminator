package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.LevelHistoryCreateRequest;
import com.kenzie.appserver.controller.model.LevelHistoryResponse;
import com.kenzie.appserver.service.LevelHistoryService;
import com.kenzie.appserver.service.model.LevelHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/levelhistory")
public class LevelHistoryController {
    private LevelHistoryService levelHistoryService;


    // TODO Constructor
    LevelHistoryController(LevelHistoryService levelHistoryService){
        this.levelHistoryService = levelHistoryService;
    }


    // TODO Methods
    @PostMapping
    public ResponseEntity<LevelHistoryResponse> addLevelToHistory(@RequestBody LevelHistoryCreateRequest levelHistoryCreateRequest) {
        LevelHistory levelHistory = new LevelHistory(levelHistoryCreateRequest.getGameId(), levelHistoryCreateRequest.getLevelNumber(),
                                                    levelHistoryCreateRequest.getCompletionTime());
        levelHistoryService.addLevelToHistory(levelHistory);

        LevelHistoryResponse levelHistoryResponse = createLevelHistoryResponse(levelHistory);

        return ResponseEntity.created(URI.create("/levelhistory/" + levelHistoryResponse.getGameId())).body(levelHistoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<LevelHistoryResponse>> getAllLevelHistory() {
        List<LevelHistory> levelHistory = levelHistoryService.getAllLevelHistory();

        // If there is no level history, then return a 204
        if (levelHistory == null ||  levelHistory.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Otherwise, convert the List of LevelHistory objects into a List of LevelHistoryResponses and return it
        List<LevelHistoryResponse> responses = new ArrayList<>();
        for (LevelHistory level : levelHistory) {
            responses.add(this.createLevelHistoryResponse(level));
        }

        return ResponseEntity.ok(responses);
    }

    private LevelHistoryResponse createLevelHistoryResponse(LevelHistory levelHistory) {
        LevelHistoryResponse levelHistoryResponse = new LevelHistoryResponse();

        levelHistoryResponse.setGameId(levelHistory.getGameId());
        levelHistoryResponse.setLevelNumber(levelHistory.getLevelNumber());
        levelHistoryResponse.setCompletionTime(levelHistory.getCompletionTime());

        return levelHistoryResponse;
    }
}
