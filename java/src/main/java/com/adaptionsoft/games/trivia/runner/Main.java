package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Printer;
import com.adaptionsoft.games.uglytrivia.Util.PlayerList;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(System.out);
        PlayerList playerList = new PlayerList();
        Game game = new Game(printer, playerList);
        Random random = new Random();
        GameRunner gameRunner = new GameRunner(game, random);
        gameRunner.start();
    }
}
