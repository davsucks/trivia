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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(HierarchicalContextRunner.class)
public class GameIntegrationTest {

    private Game game;
    private Printer mockedPrinter;
    private Player vishal;
    private Player david;

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
            vishal = new Player("Vishal");
            game.add(vishal);
        }
    }

    @Ignore("not a test")
    public class TwoPlayerSetup extends OnePlayerSetup {
        @Before
        public void setup() {
            super.setup();
            david = new Player("David");
            game.add(david);
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

                assertThat(vishal.getCurrentPlace(), is(2));
            }

            @Test
            public void shouldWrapBackToZeroWhenRollingMoreThanTheMaxNumberOfSpaces() {
                game.roll(MAX_NUMBER_OF_SPACES + 1);

                assertThat(vishal.getCurrentPlace(), is(0));
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
                game.currentPlayerIndex = FIRST_PLAYER_INDEX;

                game.wrongAnswer();

                assertTrue(game.inPenaltyBox[FIRST_PLAYER_INDEX]);
            }

            @Test
            public void shouldNotIncreaseCurrentPlayerIfThereIsOnlyOnePlayer() {
                game.wrongAnswer();

                assertThat(game.currentPlayerIndex, is(0));
            }
        }

        public class WithTwoPlayers extends TwoPlayerSetup {
            @Test
            public void shouldIncreasePlayerCountWhenCallingWrongAnswer() {
                game.wrongAnswer();

                assertThat(game.currentPlayerIndex, is(1));
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

    public class Interface {
        public class Add {
            @Test
            public void shouldPrintThatThePlayerWasAddedAndPlayerNumber() {
                david = mock(Player.class);
                when(david.toString()).thenReturn("David");
                game.add(david);

                verify(mockedPrinter).printLine("David was added");
                verify(mockedPrinter).printLine("They are player number 1");
            }
        }

        public class Roll {
            public class WithOnePlayer extends OnePlayerSetup {

                @Test
                public void shouldPrintTheCurrentPlayer() {
                    game.roll(1);

                    verify(mockedPrinter).printLine("Vishal is the current player");
                }

                @Test
                public void shouldPrintTheRoll() {
                    game.roll(5);

                    verify(mockedPrinter).printLine("They have rolled a 5");
                }

                @Test
                public void shouldPrintTheNewLocation() {
                    game.roll(1);

                    verify(mockedPrinter).printLine("Vishal's new location is 1");
                }

                @Test
                public void shouldPrintTheNewCategory() {
                    game.roll(1);

                    verify(mockedPrinter).printLine("The category is Science");
                }

                @Test
                public void shouldAskAScienceQuestion() {
                    game.roll(1);

                    verify(mockedPrinter).printLine("Science Question 0");
                }

                public class WhenInThePenaltyBox {
                    @Before
                    public void setup() {
                        game.inPenaltyBox[FIRST_PLAYER_INDEX] = true;
                    }

                    @Test
                    public void shouldBeToldWhenTheyAreGettingOutOfThePenaltyBox() {
                        game.roll(3);

                        verify(mockedPrinter).printLine("Vishal is getting out of the penalty box");
                    }

                    @Test
                    public void shouldSayWhenPlayerIsNotGettingOutOfPenaltyBox() {
                        game.roll(2);

                        verify(mockedPrinter).printLine("Vishal is not getting out of the penalty box");
                    }

                    @Test
                    public void shouldPrintTheNewLocation() {
                        game.roll(3);

                        verify(mockedPrinter).printLine("Vishal's new location is 3");
                    }

                    @Test
                    public void shouldPrintTheCurrentCategory() {
                        game.roll(1);

                        verify(mockedPrinter).printLine("The category is Science");
                    }

                    @Test
                    public void shouldAskAScienceQuestion() {
                        game.roll(1);

                        verify(mockedPrinter).printLine("Science Question 0");
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
