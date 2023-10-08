package com.kenzie.appserver.exampleGame;

import java.util.*;

public class Game {
    private PlayingBoard playingBoard;
    private List<HitPositions> availablePlayerHitPositions;
    private List<HitPositions> availableAlienHitPositions;


    // TODO Constructors
    public Game() {
        this.playingBoard = new PlayingBoard();
        this.availablePlayerHitPositions = new ArrayList<>(EnumSet.allOf(HitPositions.class));
        this.availableAlienHitPositions = new ArrayList<>(EnumSet.allOf(HitPositions.class));
    }


    // TODO Methods
    public int findHitPositionInt(HitPositions hitPosition) {
        int response = 6;

        switch (hitPosition) {
            case TOP_LEFT:
                response = 0;
                break;
            case TOP_MIDDLE:
                response = 1;
                break;
            case TOP_RIGHT:
                response = 2;
                break;
            case BOTTOM_LEFT:
                response = 3;
                break;
            case BOTTOM_MIDDLE:
                response = 4;
                break;
            case BOTTOM_RIGHT:
                response = 5;
                break;
        }

        return response;
    }
    public Game tryToPlaceShip(Game game, int yCoordinate, int xCoordinate, List<HitPositions> availablePlayerHitPositions) {
        Scanner scanner = new Scanner(System.in);

        try {
            game.playingBoard.placeShip(yCoordinate, xCoordinate, availablePlayerHitPositions);

        } catch (IllegalArgumentException e) {
            System.out.println("Ship cant be placed there (Maximum Coordinates: 9,3)");
            System.out.println("Where would you like to place your ship (Top Left Coordinate)?");

            String fixedResponse = scanner.nextLine();
            String[] fixedCoordinates = fixedResponse.split(",");
            int fixedYCoordinate = Integer.valueOf(fixedCoordinates[0]);
            int fixedXCoordinate = Integer.valueOf(fixedCoordinates[1]);
            game = tryToPlaceShip(game, fixedYCoordinate, fixedXCoordinate, availablePlayerHitPositions);
        }

        return game;
    }
    public Game checkCoordinates(Game game, int yCoordinate, int xCoordinate, List<HitPositions> availablePlayerHitPositions) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(game.playingBoard);
        System.out.println("Does this look right? Yes or No.");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Great! The Alien is choosing its position now");

        } else {
            game.playingBoard.displaceShip(yCoordinate, xCoordinate);
            System.out.println("Where would you like to place it (Top Left Coordinate)?");

            String fixedResponse = scanner.nextLine();
            String[] coordinates = fixedResponse.split(",");
            int yCoordinate2 = Integer.valueOf(coordinates[0]);
            int xCoordinate2 = Integer.valueOf(coordinates[1]);

            game = tryToPlaceShip(game, yCoordinate2, xCoordinate2, availablePlayerHitPositions);
            game = checkCoordinates(game, yCoordinate2, xCoordinate2, availablePlayerHitPositions);
        }

        return game;
    }

    public void loadLevel(int level, int alienHitPercentage, String alienShipUrl) throws InterruptedException {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Game thisGame = new Game();

        // Start up
        System.out.println("Example Coordinates (Down, Over): 5,3 = X tile");
        thisGame.playingBoard.placeExampleTile();
        System.out.println(thisGame.playingBoard);
        System.out.println("Where would you like to place your ship (Top Left Coordinate)?");

        // First ship placement
        String response = scanner.nextLine();
        String[] coordinates = response.split(",");
        int yCoordinate = Integer.valueOf(coordinates[0]);
        int xCoordinate = Integer.valueOf(coordinates[1]);
        thisGame.playingBoard.fixExampleTile();
        thisGame = tryToPlaceShip(thisGame, yCoordinate, xCoordinate, thisGame.availablePlayerHitPositions);

        // If coordinates are valid alien ship will be placed
        thisGame = checkCoordinates(thisGame, yCoordinate, xCoordinate, thisGame.availablePlayerHitPositions);
        String alienCoordinates = thisGame.playingBoard.randomizeAlienPlacement();
        String[] alienXAndY = alienCoordinates.split(",");
        thisGame.playingBoard.placeAlienShip(Integer.valueOf(alienXAndY[0]), Integer.valueOf(alienXAndY[1]), thisGame.availableAlienHitPositions);
        Thread.sleep(1500);

        // Player firing first shot
        System.out.println("Where would you like to fire? (between 1,6 - 10,10)");
        String response2 = scanner.nextLine();
        String[] coordinates2 = response2.split(",");
        int yCoordinate2 = Integer.valueOf(coordinates2[0]);
        int xCoordinate2 = Integer.valueOf(coordinates2[1]);

        // if coordinates invalid try again
        if (yCoordinate2 < 1 || yCoordinate2 > 10 || xCoordinate2 <= 5 || xCoordinate2 > 10) {
            System.out.println("You cant fire there. Where would you like to fire? (between 1,6 - 10,10)");
            String fixedResponse2 = scanner.nextLine();
            String[] fixedCoordinates2 = fixedResponse2.split(",");
            int fixedYCoordinate2 = Integer.valueOf(fixedCoordinates2[0]);
            int fixedXCoordinate2 = Integer.valueOf(fixedCoordinates2[1]);

            // if coordinates wrong again shot is forfeit
            if (fixedYCoordinate2 < 1 || fixedYCoordinate2 > 10 || fixedXCoordinate2 <= 5 || fixedXCoordinate2 > 10) {
                System.out.println("You can't fire there either, your shot is forfeit");

                // else set the original coordinates to the new ones and shoot
            } else {
                yCoordinate2 = fixedYCoordinate2;
                xCoordinate2 = fixedXCoordinate2;

                HitPositions partHit = thisGame.playingBoard.takeShot(yCoordinate2, xCoordinate2, Integer.valueOf(alienXAndY[0]), Integer.valueOf(alienXAndY[1]));
                Thread.sleep(1500);

                if (partHit == null) {
                    System.out.println("Miss");
                } else {
                    System.out.println("Hit" + partHit);
                    thisGame.availableAlienHitPositions.remove(findHitPositionInt(partHit));
                }
            }

            // else take the shot
        } else {
            HitPositions partHit = thisGame.playingBoard.takeShot(yCoordinate2, xCoordinate2, Integer.valueOf(alienXAndY[0]), Integer.valueOf(alienXAndY[1]));
            Thread.sleep(1500);

            if (partHit == null) {
                System.out.println("Miss");
            } else {
                System.out.println("Hit!");
                thisGame.availableAlienHitPositions.remove(findHitPositionInt(partHit));
            }
        }

        // Alien firing first shot
        System.out.println("The Alien is firing now");
        int alienShot1 = thisGame.playingBoard.decideAlienShot(alienHitPercentage);
        Thread.sleep(1500);

        // if 0 then miss
        if (alienShot1 == 0) {
            String missedCoordinates = thisGame.playingBoard.missAlienShot(yCoordinate, xCoordinate);
            String[] xAndY = missedCoordinates.split(",");
            thisGame.playingBoard.takeAlienShot(Integer.valueOf(xAndY[0]), Integer.valueOf(xAndY[1]), yCoordinate, xCoordinate);

            System.out.println("Alien has missed: " + Integer.valueOf(xAndY[0]) + "," + Integer.valueOf(xAndY[1]));

            // if 1 then hit
        } else {
            Map<HitPositions, String> playerCoordinates = thisGame.playingBoard.findPlayerCoordinates(yCoordinate, xCoordinate);
            int randNum = random.nextInt(thisGame.availablePlayerHitPositions.size());
            String hitCoordinates = playerCoordinates.get(thisGame.availablePlayerHitPositions.remove(randNum));
            String[] xAndY = hitCoordinates.split(",");
            thisGame.playingBoard.takeAlienShot(Integer.valueOf(xAndY[0]), Integer.valueOf(xAndY[1]), yCoordinate, xCoordinate);

            System.out.println("Alien has hit: " + Integer.valueOf(xAndY[0]) + "," + Integer.valueOf(xAndY[1]));
        }
        Thread.sleep(1500);

        // Game standings after round 1
        System.out.println("After round 1/20: ");
        System.out.println(thisGame.playingBoard);
        System.out.println("Player Health: " + thisGame.availablePlayerHitPositions.size() + "/6");
        System.out.println("Alien Health: " + thisGame.availableAlienHitPositions.size() + "/6");

        // Clear board so ships can be placed in new spots
        thisGame.playingBoard.displaceShip(yCoordinate, xCoordinate);
        thisGame.playingBoard.displaceShip(Integer.valueOf(alienXAndY[0]), Integer.valueOf(alienXAndY[1]));

        // Loop for other 19 rounds
        for (int i = 0; i < 19; i++) {
            // If player is out of spots to hit end game in loss
            if (thisGame.availablePlayerHitPositions.size() == 0) {
                System.out.println("You have been destroyed!");
                System.out.println("GAME OVER!");

                break;

                // If alien is out of spot to hit end game in win
            } else if (thisGame.availableAlienHitPositions.size() == 0) {
                System.out.println("You exterminated the alien!");
                System.out.println("YOU WIN ROUND " + level + "!");

                break;
            }

            // Start of new round
            System.out.println("Where would you like to place your ship for this round (Top Left Coordinate)?");

            // Player ship placement
            String roundResponse = scanner.nextLine();
            String[] roundCoordinates = roundResponse.split(",");
            int roundYCoordinate = Integer.valueOf(roundCoordinates[0]);
            int roundXCoordinate = Integer.valueOf(roundCoordinates[1]);
            thisGame = tryToPlaceShip(thisGame, roundYCoordinate, roundXCoordinate, thisGame.availablePlayerHitPositions);

            // If coordinates are valid alien ship will be placed
            thisGame = checkCoordinates(thisGame, roundYCoordinate, roundXCoordinate, thisGame.availablePlayerHitPositions);
            String roundAlienCoordinates = thisGame.playingBoard.randomizeAlienPlacement();
            String[] roundAlienXAndY = roundAlienCoordinates.split(",");
            thisGame.playingBoard.placeAlienShip(Integer.valueOf(roundAlienXAndY[0]), Integer.valueOf(roundAlienXAndY[1]), thisGame.availableAlienHitPositions);
            Thread.sleep(1500);

            // Player firing shot
            System.out.println("Where would you like to fire? (between 1,6 - 10,10)");
            String roundResponse2 = scanner.nextLine();
            String[] roundCoordinates2 = roundResponse2.split(",");
            int roundYCoordinate2 = Integer.valueOf(roundCoordinates2[0]);
            int roundXCoordinate2 = Integer.valueOf(roundCoordinates2[1]);

            // if coordinates invalid try again
            if (roundYCoordinate2 < 1 || roundYCoordinate2 > 10 || roundXCoordinate2 <= 5 || roundXCoordinate2 > 10) {
                System.out.println("You cant fire there. Where would you like to fire? (between 1,6 - 10,10)");
                String roundFixedResponse = scanner.nextLine();
                String[] roundFixedCoordinates = roundFixedResponse.split(",");
                int roundFixedYCoordinate = Integer.valueOf(roundFixedCoordinates[0]);
                int roundFixedXCoordinate = Integer.valueOf(roundFixedCoordinates[1]);

                // if coordinates wrong again shot is forfeit
                if (roundFixedYCoordinate < 1 || roundFixedYCoordinate > 10 || roundFixedXCoordinate <= 5 || roundFixedXCoordinate > 10) {
                    System.out.println("You can't fire there either, your shot is forfeit");

                    // else set the original coordinates to the new ones and shoot
                } else {
                    roundYCoordinate2 = roundFixedYCoordinate;
                    roundXCoordinate2 = roundFixedXCoordinate;

                    HitPositions partHit = thisGame.playingBoard.takeShot(roundYCoordinate2, roundXCoordinate2, Integer.valueOf(roundAlienXAndY[0]), Integer.valueOf(roundAlienXAndY[1]));
                    Thread.sleep(1500);

                    if (partHit == null) {
                        System.out.println("Miss");
                    } else {
                        System.out.println("Hit" + partHit);
                        thisGame.availableAlienHitPositions.remove(findHitPositionInt(partHit));
                    }
                }

                // else take the shot
            } else {
                HitPositions partHit = thisGame.playingBoard.takeShot(roundYCoordinate2, roundXCoordinate2, Integer.valueOf(roundAlienXAndY[0]), Integer.valueOf(roundAlienXAndY[1]));
                Thread.sleep(1500);

                if (partHit == null) {
                    System.out.println("Miss");
                } else {
                    System.out.println("Hit!");
                    thisGame.availableAlienHitPositions.remove(findHitPositionInt(partHit));
                }
            }

            // Alien firing first shot
            System.out.println("The Alien is firing now");
            int roundAlienShot = thisGame.playingBoard.decideAlienShot(alienHitPercentage);
            Thread.sleep(1500);

            // if 0 then miss
            if (roundAlienShot == 0) {
                String missedCoordinates = thisGame.playingBoard.missAlienShot(roundYCoordinate, roundXCoordinate);
                String[] xAndY = missedCoordinates.split(",");
                thisGame.playingBoard.takeAlienShot(Integer.valueOf(xAndY[0]), Integer.valueOf(xAndY[1]), roundYCoordinate, roundXCoordinate);

                System.out.println("Alien has missed: " + Integer.valueOf(xAndY[0]) + "," + Integer.valueOf(xAndY[1]));

                // if 1 then hit
            } else {
                Map<HitPositions, String> playerCoordinates = thisGame.playingBoard.findPlayerCoordinates(roundYCoordinate, roundXCoordinate);
                int randNum = random.nextInt(thisGame.availablePlayerHitPositions.size());
                String hitCoordinates = playerCoordinates.get(thisGame.availablePlayerHitPositions.remove(randNum));
                String[] xAndY = hitCoordinates.split(",");
                thisGame.playingBoard.takeAlienShot(Integer.valueOf(xAndY[0]), Integer.valueOf(xAndY[1]), roundYCoordinate, roundXCoordinate);

                System.out.println("Alien has hit: " + Integer.valueOf(xAndY[0]) + "," + Integer.valueOf(xAndY[1]));
            }
            Thread.sleep(1500);

            // Game standings after round
            System.out.println("After round " + (i + 2) + "/20:");
            System.out.println(thisGame.playingBoard);
            System.out.println("Player Health: " + thisGame.availablePlayerHitPositions.size() + "/6");
            System.out.println("Alien Health: " + thisGame.availableAlienHitPositions.size() + "/6");

            // Clear board so ships can be placed in new spots
            thisGame.playingBoard.displaceShip(roundYCoordinate, roundXCoordinate);
            thisGame.playingBoard.displaceShip(Integer.valueOf(roundAlienXAndY[0]), Integer.valueOf(roundAlienXAndY[1]));
        }

        int availablePlayerPieces = thisGame.availablePlayerHitPositions.size();
        int availableAlienPieces = thisGame.availableAlienHitPositions.size();

        System.out.println("You guys have both ran out of shots");

        if (availablePlayerPieces > availableAlienPieces) {
            System.out.println("You destroyed more of the alien's ship so YOU WIN!");
        } else if (availablePlayerPieces == availableAlienPieces) {
            System.out.println("You guys destroyed the same amount on both ships so YOU TIE");
        } else {
            System.out.println("The alien has destroyed more of your ship so YOU LOSE");
        }
    }
}
