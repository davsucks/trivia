package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("David");
    }

    @Test
    public void shouldStartWithZeroCoinsByDefault() {
        assertThat(player.getCoins(), is(0));
    }

    @Test
    public void shouldStartOutsideOfThePenaltyBoxByDefault() {
        assertThat(player.isInPenaltyBox(), is(false));
    }

    @Test
    public void shouldStartOnPlaceZeroByDefault() {
        assertThat(player.getCurrentPlace(), is(0));
    }

    @Test
    public void shouldMovePlayerToTheDesiredPlace() {
        player.moveTo(3);

        assertThat(player.getCurrentPlace(), is(3));
    }
}