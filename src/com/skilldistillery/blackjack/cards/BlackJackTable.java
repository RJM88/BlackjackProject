package com.skilldistillery.blackjack.cards;

import java.util.Scanner;

import com.skilldistillery.common.cards.Card;

public class BlackJackTable {
	Dealer bjDealer = new Dealer();
	Player bjPlayer = new Player();
	Scanner kb = new Scanner(System.in);

	public BlackJackTable() {
		super();
	}

	public static void main(String[] args) {
		BlackJackTable bJTable = new BlackJackTable();
		System.out.println("Welcome to the BlackJack Table, please take a seat.");
		bJTable.dealerDeals();
		bJTable.firstRound();

	}

	public void dealerDeals() {
		bjDealer.shuffleBjDeck();
		bjPlayer.takeCard(bjDealer.deal());
		bjDealer.takeCard(bjDealer.deal());
		bjPlayer.takeCard(bjDealer.deal());
		bjDealer.takeCard(bjDealer.deal());
	}


	public void firstRound() {
		// start the deal
		// Player gets the first two cards and value
		int handValue = bjPlayer.getPlayerHandValue();

		// dealer gets two cards and shows the last.
		int dealerValueSecondCard = bjDealer.getBjH().getCards().get(0).getValue();

		// Displaying player and dealer cards.
		System.out.println("Player " + bjPlayer.toString() + " - " + handValue);
		System.out.println("Dealer " + bjDealer.getBjH().getCards().get(0) + " - " + dealerValueSecondCard);

		// TODO dealer checing for blackJack.
		if (!bjPlayer.blackJackCheck()) {
			gameLogic();
		} else if (bjPlayer.blackJackCheck()) {
			System.out.println("You have BlackJack!");
		} else if (!bjDealer.blackJackCheck()) {
			gameLogic();

		} else if (bjDealer.blackJackCheck())
			System.out.println("You have BlackJack!");
	}

	public BlackJackHand playerTurn(Player currentPlayer) {
		boolean playKeepPlaying = true;
		while (playKeepPlaying) {
			System.out.println("Hit?");
			switch (kb.next()) {
			case "Y":
				currentPlayer.takeCard(hit());
				System.out.println("Player " + currentPlayer.toString() + " - " + currentPlayer.getPlayerHandValue());
				if (currentPlayer.getBjH().isBust()) {
					playKeepPlaying = false;
					break;
				}
				break;

			case "N":
				playKeepPlaying = false;
				break;
			default:
				System.out.println("Not an option.");
			}

		}
		return currentPlayer.getBjH();
	}

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

	public Card hit() {
		return bjDealer.deal();

	}

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