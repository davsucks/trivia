package com.adaptionsoft.games.uglytrivia;

public class Player {
    private int coins;
    private boolean inPenaltyBox;
    private String name;

    public Player(int coins, boolean inPenaltyBox, String name) {
        this.coins = coins;
        this.inPenaltyBox = inPenaltyBox;
        this.name = name;
    }

    Player() {
        this.coins = 0;
        this.inPenaltyBox = false;
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
}
