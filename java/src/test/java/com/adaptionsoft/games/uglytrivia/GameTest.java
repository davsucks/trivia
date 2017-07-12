package com.adaptionsoft.games.uglytrivia;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@RunWith(HierarchicalContextRunner.class)
public class GameTest {

    private Game game;

    private final int FIRST_PLAYER_INDEX = 0;
    private final int MAX_NUMBER_OF_SPACES = 11;

    @Before
    public void given() throws Exception {
        game = new Game();
    }

    @Test
    public void shouldAddFiftyQuestions() {
        Game game = new Game();

        assertEquals(game.popQuestions.size(), 50);
        assertEquals(game.scienceQuestions.size(), 50);
        assertEquals(game.sportsQuestions.size(), 50);
        assertEquals(game.rockQuestions.size(), 50);
    }

    @Test
    public void shouldHaveZeroPlayers() {
        assertThat(game.howManyPlayers(), is(0));
    }

    public class WithOnePlayer {
        @Before
        public void setup() {
            game.add("Vishal");
        }

        @Test
        public void shouldHaveOnePlayer() {
            assertThat(game.howManyPlayers(), is(1));
        }

        @Test
        public void shouldStartPlayerFromPlaceZero() {
            assertThat(game.places[FIRST_PLAYER_INDEX], is(0));
        }

        @Test
        public void shouldStartPlayerWithEmptyPurse() {
            assertEquals(game.purses[FIRST_PLAYER_INDEX], 0);
        }

        @Test
        public void shouldStartPlayerOutsideOfPenaltyBox() {
            assertFalse(game.inPenaltyBox[FIRST_PLAYER_INDEX]);
        }

        @Test
        public void shouldPutPlayerInPenaltyBoxWhenQuestionAnsweredIncorrectly() {
            game.currentPlayer = FIRST_PLAYER_INDEX;

            game.wrongAnswer();

            assertTrue(game.inPenaltyBox[FIRST_PLAYER_INDEX]);
        }

        @Test
        public void shouldNotIncreaseCurrentPlayerIfThereIsOnlyOnePlayer() {
            game.wrongAnswer();

            assertThat(game.currentPlayer, is(0));
        }

        @Test
        public void shouldMovePlayerToNewPlace() {
            game.roll(2);

            assertThat(game.places[FIRST_PLAYER_INDEX], is(2));
        }

        @Test
        public void shouldWrapBackToZeroWhenRollingMoreThanTheMaxNumberOfSpaces() {
            game.roll(MAX_NUMBER_OF_SPACES + 1);

            assertThat(game.places[FIRST_PLAYER_INDEX], is(0));
        }

    }

    public class WithTwoPlayers {
        @Before
        public void setUpTwoPlayers() {
            game.add("Vishal");
            game.add("David");
        }

        @Test
        public void shouldHaveTwoPlayers() {
            assertThat(game.howManyPlayers(), is(2));
        }

        @Test
        public void shouldIncreasePlayerCountWhenCallingWrongAnswer() {
            game.wrongAnswer();

            assertThat(game.currentPlayer, is(1));
        }

    }
}
