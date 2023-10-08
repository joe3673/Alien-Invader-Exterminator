package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ShipInformationCreateRequest {
    @NotEmpty
    @JsonProperty("gameId")
    private String gameId;

    @NotEmpty
    @JsonProperty("playerCoordinates")
    private String playerCoordinates;
    @NotEmpty
    @JsonProperty("alienCoordinates")
    private String alienCoordinates;

    @Min(0)
    @JsonProperty("playerHealth")
    private int playerHealth;
    @Min(0)
    @JsonProperty("alienHealth")
    private int alienHealth;

    @Min(0)
    @JsonProperty("round")
    private int round;

    @Min(0)
    @JsonProperty("startTime")
    private long startTime;

    @Min(0)
    @JsonProperty("endTime")
    private long endTIme;


    // TODO Getters/Setters
    public String getGameId() {
        return gameId;
    }


    public String getPlayerCoordinates() {
        return playerCoordinates;
    }
    public void setPlayerCoordinates(String playerCoordinates) {
        this.playerCoordinates = playerCoordinates;
    }

    public String getAlienCoordinates() {
        return alienCoordinates;
    }
    public void setAlienCoordinates(String alienCoordinates) {
        this.alienCoordinates = alienCoordinates;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }
    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public int getAlienHealth() {
        return alienHealth;
    }
    public void setAlienHealth(int alienHealth) {
        this.alienHealth = alienHealth;
    }


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTIme() {
        return endTIme;
    }

    public void setEndTIme(long endTIme) {
        this.endTIme = endTIme;
    }
}

