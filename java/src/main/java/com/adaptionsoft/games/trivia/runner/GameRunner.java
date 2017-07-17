package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.util.Random;

public class GameRunner {

	private static boolean notAWinner;
	private Game game;
    private final Random random;

    public GameRunner(Game game, Random random) {
	    this.game = game;
        this.random = random;
    }

    public void start() {
        game.add("Chet");
        game.add("Pat");
        game.add("Sue");

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
