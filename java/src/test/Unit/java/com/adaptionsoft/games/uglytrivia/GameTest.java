package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.Util.PlayerList;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameTest {
    private static final int FIRST_PLAYER_INDEX = 0;
    private Player mockedPlayer;
    private PlayerList mockedPlayerList;
    private Printer mockedPrinter;
    private Game game;

    @Before
    public void setup() {
        mockedPlayer = mock(Player.class);
        mockedPrinter = mock(Printer.class);
        mockedPlayerList = mock(PlayerList.class);
        game = new Game(mockedPrinter, mockedPlayerList);
    }

    @Test
    public void shouldAddACoinWhenPlayerIsGettingOutOfPenaltyBox() {
        game.inPenaltyBox[FIRST_PLAYER_INDEX] = true;
        game.isGettingOutOfPenaltyBox = true;
        when(mockedPlayerList.getCurrentPlayer()).thenReturn(mockedPlayer);

        game.wasCorrectlyAnswered();

        verify(mockedPlayer).addCoin();
    }
}
