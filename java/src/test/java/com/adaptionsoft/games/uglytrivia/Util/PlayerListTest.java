package com.adaptionsoft.games.uglytrivia.Util;

import com.adaptionsoft.games.uglytrivia.Player;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerListTest {
    private Player david = new Player("David");
    private Player vishal = new Player("Vishal");
    private PlayerList players;

    @Before
    public void setup() {
        players = new PlayerList();
    }

    @Test
    public void shouldAddAPlayerToTheList() {
        players.add(david);

        assertThat(players.size(), is(1));
    }

    @Test
    public void shouldAddTwoPlayersToTheList() {
        players.add(david);
        players.add(vishal);

        assertThat(players.size(), is(2));
    }

    @Test
    public void shouldKeepTrackOfTheCurrentPlayer() {
        players.add(david);
        players.add(vishal);

        assertThat(players.getCurrentPlayer(), is(david));
    }

    @Test
    public void shouldRotateToTheNextPlayer() {
        players.add(david);
        players.add(vishal);

        players.rotatePlayers();

        assertThat(players.getCurrentPlayer(), is(vishal));
    }
}