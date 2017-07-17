package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameRunnerTest {
    Game mockedGame;
    GameRunner runner;

    @Before
    public void setup() {
        mockedGame = mock(Game.class);
        runner = new GameRunner(mockedGame);
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
}