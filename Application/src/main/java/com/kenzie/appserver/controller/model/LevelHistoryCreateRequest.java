package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class LevelHistoryCreateRequest {
    @NotEmpty
    @JsonProperty("gameId")
    private String gameId;

    @Min(0)
    @JsonProperty("level")
    private int levelNumber;

    @Min(0)
    @JsonProperty("completionTime")
    private int completionTime;


    // TODO Getters/Setters
    public String getGameId() {
        return gameId;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getCompletionTime() {
        return completionTime;
    }
    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }
}
