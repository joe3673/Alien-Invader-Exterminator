package com.kenzie.appserver.service.model;

public class LevelHistory {
    private final String gameId;

    private int levelNumber;

    private int completionTime;


    // TODO Constructors
    public LevelHistory(String gameId, int levelNumber, int completionTime) {
        this.gameId = gameId;
        this.levelNumber = levelNumber;
        this.completionTime = completionTime;
    }

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
