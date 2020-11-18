package com.skilldistillery.blackjack.cards;

import java.util.Scanner;

import com.skilldistillery.common.cards.Card;

public class BlackJackTable {
	Dealer bjDealer = new Dealer();
	Player bjPlayer = new Player();
	Scanner kb = new Scanner(System.in);
	boolean playAgain = true;

	public BlackJackTable() {
		super();
	}

	// Will display a welcome message to player.
	public void startingDisplay() {
		System.out.println(" _________________________________________________________");
		System.out.println("											              ");
		System.out.println("|   Welcome to the BlackJack Table, please take a seat.   |");
		System.out.println("											              ");
		System.out.println("|           Dealer is now dealing the cards.              |");
		System.out.println("											              ");
		System.out.println("|_________________________________________________________|");
		System.out.println();
	}

	// This method will call the stating display, call on the dealing method, and
	// play the first round.

	public void gamePlay() {

		BlackJackTable bJTable = new BlackJackTable();
		bJTable.startingDisplay();
		bJTable.dealerDeals();
		bJTable.roundOfPlay();
		while (playAgain) {
			System.out.print("Do you want to play again? Y/N: ");
			switch (kb.next().toLowerCase()) {
			case "yes":
			case "y":
				int checkThis = bJTable.bjDealer.bjDeck.checkDeckSize();
				System.err.println(checkThis);
				if (checkThis <= 10) {
					System.out.println();
					System.out.println("New deck!");
					System.out.println();
					bJTable.bjDealer.bjDeck = new BlackJackTable().bjDealer.bjDeck;
				}
				bJTable.bjPlayer.bjH.clearHand();
				bJTable.bjDealer.bjH.clearHand();
				bJTable.dealerDeals();
				bJTable.roundOfPlay();
				break;
			case "n":
			case "no":
				playAgain = false;
				System.out.println("Thank you for playing.");
				break;
			default:
				System.out.println("Not an option.");
			}
		}
	}

	// This method has the dealer shuffle the deck then deal the player and dealer
	// two cards each.
	public void dealerDeals() {
		bjDealer.shuffleBjDeck();
		bjPlayer.takeCard(bjDealer.deal());
		bjDealer.takeCard(bjDealer.deal());
		bjPlayer.takeCard(bjDealer.deal());
		bjDealer.takeCard(bjDealer.deal());
	}

	public void roundOfPlay() {
		// Player gets the first two cards and value
		int handValue = bjPlayer.getPlayerHandValue();

		// dealer gets two cards and shows the card in index 0.
		int dealerValueSecondCard = bjDealer.getBjH().getCards().get(0).getValue();

		// Displaying player and dealer cards.
		System.out.println("Player " + bjPlayer.toString() + " - " + handValue);
		System.out.println("Dealer " + bjDealer.getBjH().getCards().get(0) + " - " + dealerValueSecondCard);

		// checking for Blackjack. if yes game ends. If no then the gameLogic will be
		// called.
		if (!bjPlayer.blackJackCheck()) {
			gameLogic();
		} else if (bjPlayer.blackJackCheck()) {
			System.out.println("You have BlackJack!");
		} else if (!bjDealer.blackJackCheck()) {
			gameLogic();
		} else if (bjDealer.blackJackCheck())
			System.out.println("Dealer has BlackJack!");
	}

	// playerTurn will start a loop to allow the player to keep hitting until they
	// stay or bust.
	public BlackJackHand playerTurn(Player currentPlayer) {
		boolean playKeepPlaying = true;
		while (playKeepPlaying) {
			System.out.println();
			System.out.print("Do you want to hit? Y/N: ");
			switch (kb.next().toLowerCase()) {
			case "yes":
			case "y":
				currentPlayer.takeCard(hit());
				System.out.println("Player " + currentPlayer.toString() + " - " + currentPlayer.getPlayerHandValue());
				if (currentPlayer.getBjH().isBust()) {
					playKeepPlaying = false;
					break;
				}
				break;
			case "n":
			case "no":
				playKeepPlaying = false;
				break;
			default:
				System.out.println("Not an option.");
			}
		}
		return currentPlayer.getBjH();
	}

	// Here Dealer will go into a loop checking if they should hit, if yes hitting,
	// finally checking if they bust.
	public BlackJackHand dealerTurn(Dealer currentDealer) {
		boolean playKeepPlaying = true;
		System.out.println("Dealer " + currentDealer.toString() + " - " + currentDealer.getPlayerHandValue());
		while (playKeepPlaying) {
			if (currentDealer.shouldDealeHit()) {
				currentDealer.takeCard(hit());
				System.out.println("Dealer " + currentDealer.toString() + " - " + currentDealer.getPlayerHandValue());
				if (currentDealer.getBjH().isBust()) {
					playKeepPlaying = false;
					break;
				}
			} else {
				playKeepPlaying = false;
			}
		}
		return currentDealer.getBjH();
	}

	// This method will be called on when someone needs to hit. Having the dealer
	// deal out a card.
	public Card hit() {
		return bjDealer.deal();
	}

	/*
	 * The gameLogic will call on the player to take a turn. Then if player didn't
	 * bust, call on dealer to take there turn. Finally if the dealer didn't bust
	 * then it will compare the player and dealer hands checking for the winner.
	 */
	public void gameLogic() {
		bjPlayer.setBjH(playerTurn(bjPlayer));
		if (bjPlayer.getBjH().isBust()) {
			System.out.println("Player lost");
		} else {
			bjDealer.setBjH(dealerTurn(bjDealer));
			if (bjDealer.getBjH().isBust()) {
				System.out.println("Dealer lost");
			}
		}
		if (!bjDealer.getBjH().isBust() && !bjPlayer.getBjH().isBust()) {
			System.out.println(bjDealer.checkForWinner(bjPlayer.getBjH(), bjDealer.getBjH()));
		}
	}
}