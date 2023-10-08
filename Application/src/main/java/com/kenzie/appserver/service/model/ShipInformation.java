package com.kenzie.appserver.service.model;

public class ShipInformation {
    private final String gameId;

    private String playerCoordinates;
    private String alienCoordinates;

    private int playerHealth;
    private int alienHealth;

    private int round;

    private long startTime;

    private long endTime;



    // TODO Constructors
    public ShipInformation(String gameId, String playerCoordinates,
                           String alienCoordinates, int playerHealth, int alienHealth, int round) {
        this.gameId = gameId;
        this.playerCoordinates = playerCoordinates;
        this.alienCoordinates = alienCoordinates;
        this.playerHealth = playerHealth;
        this.alienHealth = alienHealth;
        this.round = round;
        this.startTime = System.currentTimeMillis();
        this.endTime = 0;
    }


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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
