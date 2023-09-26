package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import java.util.Objects;

public class LevelHistoryRecord {
    private String gameId;

    private int levelNumber;

    private int completionTime;


    // TODO Getters/Setters
    @DynamoDBHashKey(attributeName = "gameId")
    public String getGameId() {
        return gameId;
    }

    @DynamoDBRangeKey(attributeName = "level")
    public int getLevelNumber() {
        return levelNumber;
    }

    @DynamoDBAttribute(attributeName = "completionTime")
    public int getCompletionTime() {
        return completionTime;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    // TODO Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelHistoryRecord that = (LevelHistoryRecord) o;
        return levelNumber == that.levelNumber && completionTime == that.completionTime && gameId.equals(that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, levelNumber, completionTime);
    }
}
