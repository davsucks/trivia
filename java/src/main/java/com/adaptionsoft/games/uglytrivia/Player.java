package com.adaptionsoft.games.uglytrivia;

public class Player {
    private int coins;
    private boolean inPenaltyBox;

    public Player(int coins, boolean inPenaltyBox) {
        this.coins = coins;
        this.inPenaltyBox = inPenaltyBox;
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
}
