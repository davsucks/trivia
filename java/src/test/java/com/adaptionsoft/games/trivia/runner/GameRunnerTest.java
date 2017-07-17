package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameRunnerTest {
    private Game mockedGame;
    private Random mockedRandom;
    private GameRunner runner;

    @Before
    public void setup() {
        mockedGame = mock(Game.class);
        mockedRandom = mock(Random.class);
        runner = new GameRunner(mockedGame, mockedRandom);
    }

    @Test
    public void shouldAddChet() {
        runner.start();

        verify(mockedGame).add("Chet");
    }

    @Test
    public void shouldAddPat() {
        runner.start();

        verify(mockedGame).add("Pat");
    }

    @Test
    public void shouldAddSue() {
        runner.start();

        verify(mockedGame).add("Sue");
    }

    @Test
    public void shouldHaveOneInTenChanceOfBeingWrong() {
        when(mockedRandom.nextInt(9)).thenReturn(7);

        runner.start();

        verify(mockedGame).wrongAnswer();
    }

    @Test
    public void shouldTranslateRandomToDieRoll() {
        int randomReturnValue = 4;
        int translatedDieValue = 5;
        when(mockedRandom.nextInt(5)).thenReturn(randomReturnValue);

        runner.start();

        verify(mockedGame).roll(translatedDieValue);
    }
}
