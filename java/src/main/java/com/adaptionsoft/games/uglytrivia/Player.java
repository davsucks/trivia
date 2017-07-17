package com.adaptionsoft.games.uglytrivia;

public class Player {
    private int coins;
    private boolean inPenaltyBox;
    private String name;
    private int currentPlace;

    public Player(String name) {
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

    void moveTo(int roll) {
        currentPlace += roll;
        if (currentPlace > Game.MAXIMUM_SPACES) {
            // normalize places
            currentPlace -= Game.MAXIMUM_SPACES + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
