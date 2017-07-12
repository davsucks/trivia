package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class GameTest {

    private Game game;

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

    @Test
    public void shouldHaveOnePlayer() {
        game.add("David");

        assertThat(game.howManyPlayers(), is(1));
    }

    @Test
    public void shouldHaveTwoPlayers() {
        game.add("David");
        game.add("Vishal");

        assertThat(game.howManyPlayers(), is(2));
    }

    @Test
    public void shouldStartPlayerFromPlaceZero() {
        game.add("Vishal");

        int firstPlayerIndex = 0;
        assertThat(game.places[firstPlayerIndex], is(0));
    }

    @Test
    public void shouldStartPlayerWithEmptyPurse() {
        game.add("Vishal");

        int firstPlayerIndex = 0;
        assertEquals(game.purses[firstPlayerIndex], 0);
    }

    @Test
    public void shouldStartPlayerOutsideOfPenaltyBox() {
        game.add("Vishal");

        int firstPlayerIndex = 0;
        assertFalse(game.inPenaltyBox[firstPlayerIndex]);
    }

    @Test
    public void shouldPutPlayerInPenaltyBoxWhenQuestionAnsweredIncorrectly() {
        int firstPlaceIndex = 0;
        game.add("Vishal");
        game.currentPlayer = firstPlaceIndex;

        game.wrongAnswer();

        assertTrue(game.inPenaltyBox[firstPlaceIndex]);
    }

    @Test
    public void shouldIncreasePlayerCountWhenCallingWrongAnswer() {
        game.add("Vishal");
        game.add("David");

        game.wrongAnswer();

        assertThat(game.currentPlayer, is(1));
    }

    @Test
    public void shouldNotIncreaseCurrentPlayerIfThereIsOnlyOnePlayer() {
        game.add("Vishal");

        game.wrongAnswer();

        assertThat(game.currentPlayer, is(0));
    }
}