package com.kenzie.appserver.exampleGame;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.loadLevel(1, 20, "alienShipUrl");
    }
}
