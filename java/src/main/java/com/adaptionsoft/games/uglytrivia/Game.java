package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    private ArrayList<String> players = new ArrayList<String>();
    private Printer printer;

    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();
    
    int currentPlayer = 0;
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
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

		printLine(playerName + " was added");
		printLine("They are player number " + players.size());
	}

	int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		printLine(players.get(currentPlayer) + " is the current player");
		printLine("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				printLine(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11)
					places[currentPlayer] = places[currentPlayer] - 12;

				printLine(players.get(currentPlayer)
						+ "'s new location is "
						+ places[currentPlayer]);
				printLine("The category is " + currentCategory());
				askQuestion();
			} else {
				printLine(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
            }
		} else {
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11)
				places[currentPlayer] = places[currentPlayer] - 12;

			printLine(players.get(currentPlayer)
					+ "'s new location is "
					+ places[currentPlayer]);
			printLine("The category is " + currentCategory());
			askQuestion();
		}
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
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]) {
			if (isGettingOutOfPenaltyBox) {
				printLine("Answer was correct!!!!");
				purses[currentPlayer]++;
				printLine(players.get(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
		} else {
			printLine("Answer was correct!!!!");
			purses[currentPlayer]++;
			printLine(players.get(currentPlayer)
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer() {
		printLine("Question was incorrectly answered");
		printLine(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}

	private void printLine(String lineToPrint) {
		printer.printLine(lineToPrint);
	}
}
