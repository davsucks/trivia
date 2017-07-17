package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;

import java.util.Random;

public class GameRunner {

    private Game game;
    private final Random random;

    GameRunner(Game game, Random random) {
	    this.game = game;
        this.random = random;
    }

    void start() {
        Player chet = new Player("Chet");
        Player pat = new Player("Pat");
        Player sue = new Player("Sue");

        game.add(chet);
        game.add(pat);
        game.add(sue);

        boolean notAWinner;
        do {
            game.roll(random.nextInt(5) + 1);

            if (random.nextInt(9) == 7) {
                notAWinner = game.wrongAnswer();
            } else {
                notAWinner = game.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}
