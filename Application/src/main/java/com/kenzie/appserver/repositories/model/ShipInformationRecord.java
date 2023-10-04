package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import java.util.Objects;

@DynamoDBTable(tableName = "ShipInformation")
public class ShipInformationRecord {
    private String gameId;

    private String playerCoordinates;
    private String alienCoordinates;

    private int playerHealth;
    private int alienHealth;

    private int round;

    private long startTime;

    private long endTime;


    // TODO Getters/Setters
    @DynamoDBHashKey(attributeName = "gameId")
    public String getGameId() {
        return gameId;
    }

    @DynamoDBAttribute(attributeName = "playerCoordinates")
    public String getPlayerCoordinates() {
        return playerCoordinates;
    }

    @DynamoDBAttribute(attributeName = "alienCoordinates")
    public String getAlienCoordinates() {
        return alienCoordinates;
    }

    @DynamoDBAttribute(attributeName = "playerHealth")
    public int getPlayerHealth() {
        return playerHealth;
    }

    @DynamoDBAttribute(attributeName = "alienHealth")
    public int getAlienHealth() {
        return alienHealth;
    }

    @DynamoDBAttribute(attributeName = "round")
    public int getRound() {return round;}

    @DynamoDBAttribute(attributeName = "startTime")
    public long getStartTime() {
        return startTime;
    }

    @DynamoDBAttribute(attributeName = "endTime")
    public long getEndTime() {
        return endTime;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setPlayerCoordinates(String playerCoordinates) {
        this.playerCoordinates = playerCoordinates;
    }
    public void setAlienCoordinates(String alienCoordinates) {
        this.alienCoordinates = alienCoordinates;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }
    public void setAlienHealth(int alienHealth) {
        this.alienHealth = alienHealth;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    // TODO Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShipInformationRecord exampleRecord = (ShipInformationRecord) o;
        return Objects.equals(gameId, exampleRecord.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }
}
