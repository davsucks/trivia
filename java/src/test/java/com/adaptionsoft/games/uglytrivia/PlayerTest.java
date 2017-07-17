package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void shouldStartWithZeroCoinsByDefault() {
        Player player = new Player("David");

        assertThat(player.getCoins(), is(0));
    }

    @Test
    public void shouldStartOutsideOfThePenaltyBoxByDefault() {
        Player player = new Player("David");

        assertThat(player.isInPenaltyBox(), is(false));
    }

    @Test
    public void shouldStartOnPlaceZeroByDefault() {
        Player player = new Player("David");

        assertThat(player.getCurrentPlace(), is(0));
    }
}