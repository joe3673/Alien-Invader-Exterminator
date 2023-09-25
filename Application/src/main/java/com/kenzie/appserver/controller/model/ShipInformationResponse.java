package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipInformationResponse {


    @JsonProperty("id")
    private String gameId;

    @JsonProperty("playerCoordinates")
    private String playerCoordinates;

    @JsonProperty("alienCoordinates")
    private String alienCoordinates;

    @JsonProperty("playerHealth")
    private int playerHealth;

    @Min(0)
    @JsonProperty("alienHealth")
    private int alienHealth;

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
