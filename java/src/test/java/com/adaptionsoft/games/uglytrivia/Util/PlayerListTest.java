package com.adaptionsoft.games.uglytrivia.Util;

import com.adaptionsoft.games.uglytrivia.Player;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerListTest {
    @Test
    public void shouldAddAPlayerToTheList() {
        Player newPlayer = new Player("David");

        PlayerList players = new PlayerList();
        players.add(newPlayer);

        assertThat(players.size(), is(1));
    }

    @Test
    public void shouldAddTwoPlayersToTheList() {
        Player newPlayer1 = new Player("David");
        Player newPlayer2 = new Player("Vishal");

        PlayerList players = new PlayerList();
        players.add(newPlayer1);
        players.add(newPlayer2);

        assertThat(players.size(), is(2));
    }

    @Test
    public void shouldKeepTrackOfTheCurrentPlayer() {
        Player newPlayer1 = new Player("David");
        Player newPlayer2 = new Player("Vishal");

        PlayerList players = new PlayerList();
        players.add(newPlayer1);
        players.add(newPlayer2);

        assertThat(players.getCurrentPlayer(), is(newPlayer1));
    }
}