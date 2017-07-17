package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void shouldStartWithZeroCoinsByDefault() {
        Player player = new Player();

        assertThat(player.getCoins(), is(0));
    }

    @Test
    public void shouldStartOutsideOfThePenaltyBoxByDefault() {
        Player player = new Player();

        assertThat(player.isInPenaltyBox(), is(false));
    }
}