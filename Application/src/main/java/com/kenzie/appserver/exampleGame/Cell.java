package com.kenzie.appserver.exampleGame;

public class Cell {
    private boolean occupation;
    private boolean hitOrNot;


    // TODO Constructor
    public Cell() {
        this.occupation = false;
        this.hitOrNot = false;
    }

    // TODO Getter/Setter
    public boolean isOccupation() {
        return occupation;
    }
    public void setOccupation(boolean occupation) {
        this.occupation = occupation;
    }

    public boolean isHitOrNot() {
        return hitOrNot;
    }
    public void setHitOrNot(boolean hitOrNot) {
        this.hitOrNot = hitOrNot;
    }

    // TODO Methods
    @Override
    public String toString() {
        if (this.occupation == true && this.hitOrNot == true) {
            return "X";
        } else if (this.occupation == true && this.hitOrNot == false){
            return "0";
        } else {
            return "O";
        }
    }
}
