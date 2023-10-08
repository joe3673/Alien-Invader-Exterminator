package com.kenzie.appserver.exampleGame;

import java.util.*;

public class PlayingBoard {
    private Cell[][] grid;

    // TODO Constructor
    public PlayingBoard() {
        Cell[][] board = new Cell[10][10];

        int rowCounter = 0;
        int columnCounter = 0;

        for (int i = 0; i < 100; i++) {
            Cell cell = new Cell();

            if (rowCounter == 10) {
                columnCounter++;
            }

            if (rowCounter == 10) {
                rowCounter = 0;
            }

            board[columnCounter][rowCounter] = cell;

            rowCounter++;
        }

        this.grid = board;
    }


    // TODO Getter/Setter


    // TODO Methods
    public void placeExampleTile() {
        Cell exampleCell = new Cell();
        exampleCell.setOccupation(true);
        exampleCell.setHitOrNot(true);

        this.grid[4][2] = exampleCell;
    }
    public void fixExampleTile() {
        Cell exampleCell = new Cell();
        exampleCell.setOccupation(false);
        exampleCell.setHitOrNot(false);

        this.grid[4][2] = exampleCell;
    }

    public void placeShip(int topLeftYCoordinate, int topLeftXCoordinate, List<HitPositions> intactPieces) {
        if (topLeftYCoordinate > 9 || topLeftXCoordinate > 3) throw new IllegalArgumentException();

        // example coordinates: 4,2
        int yCoordinate = topLeftYCoordinate - 1;
        int xCoordinate = topLeftXCoordinate - 1;

        // 4,2
        Cell cell = new Cell();
        if (intactPieces.contains(HitPositions.TOP_LEFT)) {
            cell.setOccupation(true);
        } else {
            cell.setOccupation(true);
            cell.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell;

        xCoordinate += 1;

        // 4,3
        Cell cell2 = new Cell();
        if (intactPieces.contains(HitPositions.TOP_MIDDLE)) {
            cell2.setOccupation(true);
        } else {
            cell2.setOccupation(true);
            cell2.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell2;

        xCoordinate += 1;

        // 4,4
        Cell cell3 = new Cell();
        if (intactPieces.contains(HitPositions.TOP_RIGHT)) {
            cell3.setOccupation(true);
        } else {
            cell3.setOccupation(true);
            cell3.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell3;

        yCoordinate += 1;
        xCoordinate = topLeftXCoordinate - 1;

        // 5,2
        Cell cell4 = new Cell();
        if (intactPieces.contains(HitPositions.BOTTOM_LEFT)) {
            cell4.setOccupation(true);
        } else {
            cell4.setOccupation(true);
            cell4.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell4;

        xCoordinate += 1;

        // 5,3
        Cell cell5 = new Cell();
        if (intactPieces.contains(HitPositions.BOTTOM_MIDDLE)) {
            cell5.setOccupation(true);
        } else {
            cell5.setOccupation(true);
            cell5.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell5;

        xCoordinate += 1;

        // 5,4
        Cell cell6 = new Cell();
        if (intactPieces.contains(HitPositions.BOTTOM_RIGHT)) {
            cell6.setOccupation(true);
        } else {
            cell6.setOccupation(true);
            cell6.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell6;
    }
    public String randomizeAlienPlacement() {
        Random random = new Random();

        int yCoordinate = random.nextInt(9) + 1;
        int xCoordinate = random.nextInt(3) + 6;

        return yCoordinate + "," + xCoordinate;
    }
    public void placeAlienShip(int topLeftYCoordinate, int topLeftXCoordinate, List<HitPositions> intactPieces) {
        // example coordinates: 4,2
        int yCoordinate = topLeftYCoordinate - 1;
        int xCoordinate = topLeftXCoordinate - 1;

        if (yCoordinate >= 10 || yCoordinate < 0 || xCoordinate <= 4 || xCoordinate >= 8)
            throw new IllegalArgumentException();

        // 4,2
        Cell cell = new Cell();
        if (intactPieces.contains(HitPositions.TOP_LEFT)) {
            cell.setOccupation(true);
        } else {
            cell.setOccupation(true);
            cell.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell;

        xCoordinate += 1;

        // 4,3
        Cell cell2 = new Cell();
        if (intactPieces.contains(HitPositions.TOP_MIDDLE)) {
            cell2.setOccupation(true);
        } else {
            cell2.setOccupation(true);
            cell2.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell2;

        xCoordinate += 1;

        // 4,4
        Cell cell3 = new Cell();
        if (intactPieces.contains(HitPositions.TOP_RIGHT)) {
            cell3.setOccupation(true);
        } else {
            cell3.setOccupation(true);
            cell3.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell3;

        yCoordinate += 1;
        xCoordinate = topLeftXCoordinate - 1;

        // 5,2
        Cell cell4 = new Cell();
        if (intactPieces.contains(HitPositions.BOTTOM_LEFT)) {
            cell4.setOccupation(true);
        } else {
            cell4.setOccupation(true);
            cell4.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell4;

        xCoordinate += 1;

        // 5,3
        Cell cell5 = new Cell();
        if (intactPieces.contains(HitPositions.BOTTOM_MIDDLE)) {
            cell5.setOccupation(true);
        } else {
            cell5.setOccupation(true);
            cell5.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell5;

        xCoordinate += 1;

        // 5,4
        Cell cell6 = new Cell();
        if (intactPieces.contains(HitPositions.BOTTOM_RIGHT)) {
            cell6.setOccupation(true);
        } else {
            cell6.setOccupation(true);
            cell6.setHitOrNot(true);
        }
        this.grid[yCoordinate][xCoordinate] = cell6;
    }
    public void displaceShip(int topLeftYCoordinate, int topLeftXCoordinate) {
        // example coordinates: 4,2
        int yCoordinate = topLeftYCoordinate - 1;
        int xCoordinate = topLeftXCoordinate - 1;

        // 4,2
        Cell cell = new Cell();
        cell.setOccupation(false);
        this.grid[yCoordinate][xCoordinate] = cell;

        xCoordinate += 1;

        // 4,3
        Cell cell2 = new Cell();
        cell2.setOccupation(false);
        this.grid[yCoordinate][xCoordinate] = cell2;

        xCoordinate += 1;

        // 4,4
        Cell cell3 = new Cell();
        cell3.setOccupation(false);
        this.grid[yCoordinate][xCoordinate] = cell3;

        yCoordinate += 1;
        xCoordinate = topLeftXCoordinate - 1;

        // 5,2
        Cell cell4 = new Cell();
        cell4.setOccupation(false);
        this.grid[yCoordinate][xCoordinate] = cell4;

        xCoordinate += 1;

        // 5,3
        Cell cell5 = new Cell();
        cell5.setOccupation(false);
        this.grid[yCoordinate][xCoordinate] = cell5;

        xCoordinate += 1;

        // 5,4
        Cell cell6 = new Cell();
        cell6.setOccupation(false);
        this.grid[yCoordinate][xCoordinate] = cell6;
    }

    public int decideAlienShot(int alienHitPercentage) {
        Random random = new Random();
        int bound = 100 / alienHitPercentage;
        int rand = random.nextInt(bound + 1);
        // if random hits, it'll give 1
        if (rand == bound) {
            return 1;

            // else 0
        } else {
            return 0;
        }
    }
    public String missAlienShot(int playerYCoordinate, int playerXCoordinate) {
        Random random = new Random();
        Map<HitPositions, String> playerCoordinates = findPlayerCoordinates(playerYCoordinate, playerXCoordinate);
        List<String> allPossibleCoordinates = allPossiblePlayerCoordinates();

        String topLeft = playerCoordinates.get(HitPositions.TOP_LEFT);
        String topMiddle = playerCoordinates.get(HitPositions.TOP_MIDDLE);
        String topRight = playerCoordinates.get(HitPositions.TOP_RIGHT);
        String bottomLeft = playerCoordinates.get(HitPositions.BOTTOM_LEFT);
        String bottomMiddle = playerCoordinates.get(HitPositions.BOTTOM_MIDDLE);
        String bottomRight = playerCoordinates.get(HitPositions.BOTTOM_RIGHT);

        allPossibleCoordinates.remove(topLeft);
        allPossibleCoordinates.remove(topMiddle);
        allPossibleCoordinates.remove(topRight);
        allPossibleCoordinates.remove(bottomLeft);
        allPossibleCoordinates.remove(bottomMiddle);
        allPossibleCoordinates.remove(bottomRight);

        // returns a random coordinate the player is not in
        String randomCoordinates = allPossibleCoordinates.get(random.nextInt(allPossibleCoordinates.size()));
        String[] coordinates = randomCoordinates.split(",");
        int y = Integer.valueOf(coordinates[0]);
        int x = Integer.valueOf(coordinates[1]);
        return (y + 1) + "," + (x + 1);
    }
    public HitPositions takeAlienShot(int yCoordinate, int xCoordinate, int shipYCoordinate, int shipXCoordinate) {
        int realYCoordinate = yCoordinate - 1;
        int realXCoordinate = xCoordinate - 1;

        if (realYCoordinate > 10 && realYCoordinate < 1 || realXCoordinate > 5 && realXCoordinate < 1)
            throw new IllegalArgumentException();

        if (this.grid[realYCoordinate][realXCoordinate].isOccupation()) {
            Cell cell = new Cell();
            cell.setOccupation(true);
            cell.setHitOrNot(true);

            this.grid[realYCoordinate][realXCoordinate] = cell;

            return whichShipPieceWasHit(shipYCoordinate, shipXCoordinate);
        } else {
            return null;
        }
    }
    public HitPositions takeShot(int yCoordinate, int xCoordinate, int shipYCoordinate, int shipXCoordinate) {
        int realYCoordinate = yCoordinate - 1;
        int realXCoordinate = xCoordinate - 1;

        if (realYCoordinate > 10 || realXCoordinate < 5 && realXCoordinate > 10) throw new IllegalArgumentException();

        if (this.grid[realYCoordinate][realXCoordinate].isOccupation()) {
            Cell cell = new Cell();
            cell.setOccupation(true);
            cell.setHitOrNot(true);

            this.grid[realYCoordinate][realXCoordinate] = cell;

            return whichShipPieceWasHit(shipYCoordinate, shipXCoordinate);
        } else {
            return null;
        }
    }

    public HitPositions whichShipPieceWasHit(int yCoordinate, int xCoordinate) {
        Map<HitPositions, String> coordinates = findPlayerCoordinates(yCoordinate, xCoordinate);

        for (Map.Entry<HitPositions, String> entrySet : coordinates.entrySet()) {
            HitPositions key = entrySet.getKey();
            String value = entrySet.getValue();

            if (value.equalsIgnoreCase(yCoordinate + "," + xCoordinate)) return key;
        }
        return null;
    }

    public Map<HitPositions, String> findPlayerCoordinates(int yCoordinate, int xCoordinate) {
        String topLeft = yCoordinate + "," + xCoordinate;
        String topMiddle = yCoordinate + "," + (xCoordinate + 1);
        String topRight = yCoordinate + "," + (xCoordinate + 2);
        String bottomLeft = (yCoordinate + 1) + "," + xCoordinate;
        String bottomMiddle = (yCoordinate + 1) + "," + (xCoordinate + 1);
        String bottomRight = (yCoordinate + 1) + "," + (xCoordinate + 2);

        Map<HitPositions, String> coordinates = new HashMap<>();
        coordinates.put(HitPositions.TOP_LEFT, topLeft);
        coordinates.put(HitPositions.TOP_MIDDLE, topMiddle);
        coordinates.put(HitPositions.TOP_RIGHT, topRight);
        coordinates.put(HitPositions.BOTTOM_LEFT, bottomLeft);
        coordinates.put(HitPositions.BOTTOM_MIDDLE, bottomMiddle);
        coordinates.put(HitPositions.BOTTOM_RIGHT, bottomRight);

        return coordinates;
    }
    public static List<String> allPossiblePlayerCoordinates() {
        List<String> coordinates = new ArrayList<>();
        int yCoordinate = 0;
        int xCoordinate = 0;

        for (int i = 0; i < 50; i++) {
            if (xCoordinate == 5) {
                xCoordinate = 0;
                yCoordinate += 1;
            }

            coordinates.add(yCoordinate + "," + xCoordinate);

            xCoordinate += 1;
        }

        return coordinates;
    }

    @Override
    public String toString() {
        String space = "  ";
        String line = " | ";

        return "    1  2  3  4  5 | 6  7  8  9  10 \n" +
                "1   " + this.grid[0][0].toString() + space + this.grid[0][1].toString() + space + this.grid[0][2].toString() + space + this.grid[0][3].toString() + space + this.grid[0][4].toString() + line + this.grid[0][5].toString() + space + this.grid[0][6].toString() + space + this.grid[0][7].toString() + space + this.grid[0][8].toString() + space + this.grid[0][9].toString() + "\n" +
                "2   " + this.grid[1][0].toString() + space + this.grid[1][1].toString() + space + this.grid[1][2].toString() + space + this.grid[1][3].toString() + space + this.grid[1][4].toString() + line + this.grid[1][5].toString() + space + this.grid[1][6].toString() + space + this.grid[1][7].toString() + space + this.grid[1][8].toString() + space + this.grid[1][9].toString() + "\n" +
                "3   " + this.grid[2][0].toString() + space + this.grid[2][1].toString() + space + this.grid[2][2].toString() + space + this.grid[2][3].toString() + space + this.grid[2][4].toString() + line + this.grid[2][5].toString() + space + this.grid[2][6].toString() + space + this.grid[2][7].toString() + space + this.grid[2][8].toString() + space + this.grid[2][9].toString() + "\n" +
                "4   " + this.grid[3][0].toString() + space + this.grid[3][1].toString() + space + this.grid[3][2].toString() + space + this.grid[3][3].toString() + space + this.grid[3][4].toString() + line + this.grid[3][5].toString() + space + this.grid[3][6].toString() + space + this.grid[3][7].toString() + space + this.grid[3][8].toString() + space + this.grid[3][9].toString() + "\n" +
                "5   " + this.grid[4][0].toString() + space + this.grid[4][1].toString() + space + this.grid[4][2].toString() + space + this.grid[4][3].toString() + space + this.grid[4][4].toString() + line + this.grid[4][5].toString() + space + this.grid[4][6].toString() + space + this.grid[4][7].toString() + space + this.grid[4][8].toString() + space + this.grid[4][9].toString() + "\n" +
                "6   " + this.grid[5][0].toString() + space + this.grid[5][1].toString() + space + this.grid[5][2].toString() + space + this.grid[5][3].toString() + space + this.grid[5][4].toString() + line + this.grid[5][5].toString() + space + this.grid[5][6].toString() + space + this.grid[5][7].toString() + space + this.grid[5][8].toString() + space + this.grid[5][9].toString() + "\n" +
                "7   " + this.grid[6][0].toString() + space + this.grid[6][1].toString() + space + this.grid[6][2].toString() + space + this.grid[6][3].toString() + space + this.grid[6][4].toString() + line + this.grid[6][5].toString() + space + this.grid[6][6].toString() + space + this.grid[6][7].toString() + space + this.grid[6][8].toString() + space + this.grid[6][9].toString() + "\n" +
                "8   " + this.grid[7][0].toString() + space + this.grid[7][1].toString() + space + this.grid[7][2].toString() + space + this.grid[7][3].toString() + space + this.grid[7][4].toString() + line + this.grid[7][5].toString() + space + this.grid[7][6].toString() + space + this.grid[7][7].toString() + space + this.grid[7][8].toString() + space + this.grid[7][9].toString() + "\n" +
                "9   " + this.grid[8][0].toString() + space + this.grid[8][1].toString() + space + this.grid[8][2].toString() + space + this.grid[8][3].toString() + space + this.grid[8][4].toString() + line + this.grid[8][5].toString() + space + this.grid[8][6].toString() + space + this.grid[8][7].toString() + space + this.grid[8][8].toString() + space + this.grid[8][9].toString() + "\n" +
                "10  " + this.grid[9][0].toString() + space + this.grid[9][1].toString() + space + this.grid[9][2].toString() + space + this.grid[9][3].toString() + space + this.grid[9][4].toString() + line + this.grid[9][5].toString() + space + this.grid[9][6].toString() + space + this.grid[9][7].toString() + space + this.grid[9][8].toString() + space + this.grid[9][9].toString();
    }
}
