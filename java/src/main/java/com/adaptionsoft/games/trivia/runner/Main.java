package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Printer;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(System.out);
        Game game = new Game(printer);
        GameRunner gameRunner = new GameRunner(game);
        gameRunner.start();
    }
}
