package com.adaptionsoft.games.uglytrivia;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(HierarchicalContextRunner.class)
public class GameTest {

    private Game game;
    private Printer mockedPrinter;

    private final int FIRST_PLAYER_INDEX = 0;
    private final int MAX_NUMBER_OF_SPACES = 11;

    @Before
    public void given() throws Exception {
        mockedPrinter = mock(Printer.class);
        game = new Game(mockedPrinter);
    }

    @Ignore("not a test")
    public class OnePlayerSetup {
        @Before
        public void setup() {
            game.add("Vishal");
        }
    }

    @Ignore("not a test")
    public class TwoPlayerSetup extends OnePlayerSetup {
        @Before
        public void setup() {
            super.setup();
            game.add("David");
        }
    }

    @Test
    public void shouldAddFiftyQuestions() {
        Game game = new Game(mockedPrinter);

        assertEquals(game.popQuestions.size(), 50);
        assertEquals(game.scienceQuestions.size(), 50);
        assertEquals(game.sportsQuestions.size(), 50);
        assertEquals(game.rockQuestions.size(), 50);
    }

    public class HowManyPlayers {
        @Test
        public void shouldHaveZeroPlayers() {
            assertThat(game.howManyPlayers(), is(0));
        }

        public class WithOnePlayer extends OnePlayerSetup {
            @Test
            public void shouldHaveOnePlayer() {
                assertThat(game.howManyPlayers(), is(1));
            }

        }

        public class WithTwoPlayers extends TwoPlayerSetup {
            @Test
            public void shouldHaveTwoPlayers() {
                assertThat(game.howManyPlayers(), is(2));
            }
        }
    }

    public class Roll {
        public class WithOnePlayer extends OnePlayerSetup {
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

            @Test
            public void shouldAskAQuestion() {
                game.roll(2);

                assertTrue(aQuestionWasAsked());
            }

            public class WhenInThePenaltyBox {
                @Before
                public void setup() {
                    game.inPenaltyBox[FIRST_PLAYER_INDEX] = true;
                }

                @Test
                public void shouldBeGettingOutOfThePenaltyBoxOnAnOddRoll() {
                    game.roll(1);

                    assertThat(game.isGettingOutOfPenaltyBox, is(true));
                }

                @Test
                public void shouldNotBeGettingOutOfThePenaltyBoxOnAnEvenRoll() {
                    game.roll(2);

                    assertThat(game.isGettingOutOfPenaltyBox, is(false));
                }

                @Test
                public void shouldNotBeAskedAQuestionOnAnEvenRoll() {
                    game.roll(2);

                    assertTrue(noQuestionsWereAsked());
                }

                @Test
                public void shouldAsAQuestionOnAnOddRoll() {
                    game.roll(1);

                    assertTrue(aQuestionWasAsked());
                }
            }
        }
    }

    public class Places {
       public class WithOnePlayer extends OnePlayerSetup {
           @Test
           public void shouldStartPlayerFromPlaceZero() {
               assertThat(game.places[FIRST_PLAYER_INDEX], is(0));
           }
       }
    }

    public class Purses {
        public class WithOnePlayer extends OnePlayerSetup {
            @Test
            public void shouldStartPlayerWithEmptyPurse() {
                assertEquals(game.purses[FIRST_PLAYER_INDEX], 0);
            }
        }
    }

    public class InPenaltyBox {
        public class WithOnePlayer extends OnePlayerSetup {
            @Test
            public void shouldStartPlayerOutsideOfPenaltyBox() {
                assertFalse(game.inPenaltyBox[FIRST_PLAYER_INDEX]);
            }
        }
    }

    public class WrongAnswer {
        public class WithOnePlayer extends OnePlayerSetup {
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
        }

        public class WithTwoPlayers extends TwoPlayerSetup {
            @Test
            public void shouldIncreasePlayerCountWhenCallingWrongAnswer() {
                game.wrongAnswer();

                assertThat(game.currentPlayer, is(1));
            }
        }
    }

    public class WasCorrectlyAnswered {
        public class WithOnePlayer extends OnePlayerSetup {
            public class WhenInThePenaltyBox {
                @Before
                public void setup() {
                    game.inPenaltyBox[FIRST_PLAYER_INDEX] = true;
                }

                public class WhenGettingOutOfThePenaltyBox {
                    @Before
                    public void setup() {
                        game.isGettingOutOfPenaltyBox = true;
                    }

                    @Test
                    public void shouldIncreaseTheNumberOfCoins() {
                        game.wasCorrectlyAnswered();

                        assertThat(game.purses[FIRST_PLAYER_INDEX], is(1));
                    }

                    @Test
                    public void shouldReturnFalseIfThePlayerJustWon() {
                        game.purses[FIRST_PLAYER_INDEX] = 5;

                        boolean didPlayerNotWin = game.wasCorrectlyAnswered();

                        assertFalse(didPlayerNotWin);
                    }

                    @Test
                    public void shouldReturnTrueIfThePlayerDidNotWin() {
                        boolean didPlayerNotWin = game.wasCorrectlyAnswered();

                        assertTrue(didPlayerNotWin);
                    }
                }
            }
        }
    }

    private boolean aQuestionWasAsked() {
        return game.popQuestions.size() != 50 ||
                game.scienceQuestions.size() != 50 ||
                game.sportsQuestions.size() != 50 ||
                game.rockQuestions.size() != 50;
    }

    private boolean noQuestionsWereAsked() {
        return !aQuestionWasAsked();
    }
}
