
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Printer;


public class GameRunner {

	private static boolean notAWinner;
	private Game game;

	public GameRunner(Game game) {
	    this.game = game;
    }

    public void start() {
        game.add("Chet");
        game.add("Pat");
        game.add("Sue");

        Random rand = new Random();

        do {
            game.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = game.wrongAnswer();
            } else {
                notAWinner = game.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}
