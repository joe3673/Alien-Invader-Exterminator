package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.HitPositions;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ShipInformationUpdateRequest {
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
}
