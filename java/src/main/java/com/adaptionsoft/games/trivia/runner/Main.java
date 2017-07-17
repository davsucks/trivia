package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Printer;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(System.out);
        Game game = new Game(printer);
        Random random = new Random();
        GameRunner gameRunner = new GameRunner(game, random);
        gameRunner.start();
    }
}
