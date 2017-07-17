package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
	final static int MAXIMUM_SPACES = 11;
	private List<Player> players = new ArrayList<Player>();
    private Printer printer;

    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();
    
    int currentPlayerIndex = 0;
    boolean isGettingOutOfPenaltyBox;

	public  Game(Printer printer){
		this.printer = printer;
		for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast("Science Question " + i);
			sportsQuestions.addLast("Sports Question " + i);
			rockQuestions.addLast("Rock Question " + i);
    	}
    }

	public void add(Player newPlayer) {
	    players.add(newPlayer);
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

		printLine(newPlayer + " was added");
		printLine("They are player number " + players.size());
	}

	int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		Player currentPlayer = players.get(currentPlayerIndex);
		printLine(currentPlayer + " is the current player");
		printLine("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayerIndex]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				printLine(currentPlayer + " is getting out of the penalty box");
				movePlayer(currentPlayer, roll);

				printLine(currentPlayer
						+ "'s new location is "
						+ currentPlayer.getCurrentPlace());
				printLine("The category is " + currentCategory(currentPlayer));
				askQuestion(currentPlayer);
			} else {
				printLine(currentPlayer + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
            }
		} else {
			movePlayer(currentPlayer, roll);

			printLine(currentPlayer
					+ "'s new location is "
					+ currentPlayer.getCurrentPlace());
			printLine("The category is " + currentCategory(currentPlayer));
			askQuestion(currentPlayer);
		}
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayerIndex]) {
			if (isGettingOutOfPenaltyBox) {
				printLine("Answer was correct!!!!");
				purses[currentPlayerIndex]++;
				printLine(players.get(currentPlayerIndex)
						+ " now has "
						+ purses[currentPlayerIndex]
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
				incrementCurrentPlayer();
				
				return winner;
			} else {
				incrementCurrentPlayer();
				return true;
			}
		} else {
			printLine("Answer was correct!!!!");
			purses[currentPlayerIndex]++;
			printLine(players.get(currentPlayerIndex)
					+ " now has "
					+ purses[currentPlayerIndex]
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
			incrementCurrentPlayer();
			
			return winner;
		}
	}

	public boolean wrongAnswer() {
		printLine("Question was incorrectly answered");
		printLine(players.get(currentPlayerIndex)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayerIndex] = true;

		incrementCurrentPlayer();
		return true;
	}

	private void movePlayer(Player currentPlayer, int roll) {
		currentPlayer.moveTo(roll);
	}

	private void askQuestion(Player currentPlayer) {
		if (currentCategory(currentPlayer) == "Pop")
			printLine(popQuestions.removeFirst());
		if (currentCategory(currentPlayer) == "Science")
			printLine(scienceQuestions.removeFirst());
		if (currentCategory(currentPlayer) == "Sports")
			printLine(sportsQuestions.removeFirst());
		if (currentCategory(currentPlayer) == "Rock")
			printLine(rockQuestions.removeFirst());
	}

	private String currentCategory(Player currentPlayer) {
		// TODO: DRY this up with maths
		if (currentPlayer.getCurrentPlace() == 0) return "Pop";
		if (currentPlayer.getCurrentPlace() == 1) return "Science";
		if (currentPlayer.getCurrentPlace() == 2) return "Sports";
		if (currentPlayer.getCurrentPlace() == 4) return "Pop";
		if (currentPlayer.getCurrentPlace() == 5) return "Science";
		if (currentPlayer.getCurrentPlace() == 6) return "Sports";
		if (currentPlayer.getCurrentPlace() == 8) return "Pop";
		if (currentPlayer.getCurrentPlace() == 9) return "Science";
		if (currentPlayer.getCurrentPlace() == 10) return "Sports";
		return "Rock";
	}

	private void incrementCurrentPlayer() {
		currentPlayerIndex++;
		if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayerIndex] == 6);
	}

	private void printLine(String lineToPrint) {
		printer.printLine(lineToPrint);
	}
}
