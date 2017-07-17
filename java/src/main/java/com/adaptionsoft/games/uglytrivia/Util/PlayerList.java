package com.adaptionsoft.games.uglytrivia.Util;

import com.adaptionsoft.games.uglytrivia.Player;

import java.util.LinkedList;

public class PlayerList {
    // TODO: deque?
    LinkedList<Player> players;

    public PlayerList() {
        players = new LinkedList<Player>();
    }

    public void add(Player newPlayer) {
        players.add(newPlayer);
    }

    public int size() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        return players.getFirst();
    }
}
