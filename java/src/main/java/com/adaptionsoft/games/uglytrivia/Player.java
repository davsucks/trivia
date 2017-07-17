package com.adaptionsoft.games.uglytrivia;

public class Player {
    private int coins;
    private boolean inPenaltyBox;
    private String name;
    private int currentPlace;

    public Player(int coins, boolean inPenaltyBox, String name, int currentPlace) {
        this.coins = coins;
        this.inPenaltyBox = inPenaltyBox;
        this.name = name;
        this.currentPlace = currentPlace;
    }

    Player(String name) {
        this.coins = 0;
        this.inPenaltyBox = false;
        this.name = name;
        this.currentPlace = 0;
    }

    int getCoins() {
        return coins;
    }

    boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    @Override
    public String toString() {
        return name;
    }

    int getCurrentPlace() {
        return currentPlace;
    }
}
