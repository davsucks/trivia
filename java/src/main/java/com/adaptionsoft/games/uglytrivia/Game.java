package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<Player>();
    private Printer printer;

    int[] places = new int[6];
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

	public void add(String playerName) {
	    Player newPlayer = new Player(playerName);
	    players.add(newPlayer);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

		printLine(newPlayer + " was added");
		printLine("They are player number " + players.size());
	}

	int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		printLine(players.get(currentPlayerIndex) + " is the current player");
		printLine("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayerIndex]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				printLine(players.get(currentPlayerIndex) + " is getting out of the penalty box");
				movePlayer(roll);

				printLine(players.get(currentPlayerIndex)
						+ "'s new location is "
						+ places[currentPlayerIndex]);
				printLine("The category is " + currentCategory());
				askQuestion();
			} else {
				printLine(players.get(currentPlayerIndex) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
            }
		} else {
			movePlayer(roll);

			printLine(players.get(currentPlayerIndex)
					+ "'s new location is "
					+ places[currentPlayerIndex]);
			printLine("The category is " + currentCategory());
			askQuestion();
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

	private void movePlayer(int roll) {
		places[currentPlayerIndex] = places[currentPlayerIndex] + roll;
		if (places[currentPlayerIndex] > 11)
			places[currentPlayerIndex] = places[currentPlayerIndex] - 12;
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			printLine(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			printLine(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			printLine(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			printLine(rockQuestions.removeFirst());
	}

	private String currentCategory() {
		// TODO: DRY this up with maths
		if (places[currentPlayerIndex] == 0) return "Pop";
		if (places[currentPlayerIndex] == 1) return "Science";
		if (places[currentPlayerIndex] == 2) return "Sports";
		if (places[currentPlayerIndex] == 4) return "Pop";
		if (places[currentPlayerIndex] == 5) return "Science";
		if (places[currentPlayerIndex] == 6) return "Sports";
		if (places[currentPlayerIndex] == 8) return "Pop";
		if (places[currentPlayerIndex] == 9) return "Science";
		if (places[currentPlayerIndex] == 10) return "Sports";
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
