package com.skilldistillery.blackjack.cards;

import com.skilldistillery.common.cards.Card;
import com.skilldistillery.common.cards.Deck;

public class Dealer extends Player {
	Deck bjDeck = new Deck();

	public Dealer() {
	}

	public Dealer(Deck deck) {
		super();
		bjDeck = deck;
	}

	public void shuffleBjDeck() {
		bjDeck.shuffle();
	}

	public Card deal() {
		return bjDeck.dealCard();
	}

	public String checkForWinner(BlackJackHand player, BlackJackHand dealer) {
		if (player.getHandValue() > dealer.getHandValue()) {
			String playerWins = ("Player Wins!");
			return playerWins;
		} else if (player.getHandValue() < dealer.getHandValue()) {
			String dealerWins = ("Dealer Wins!");
			return dealerWins;
		} else {
			String itsAPush = ("It's a push!");
			return itsAPush;
		}
	}

	public boolean shouldDealeHit() {
		if (bjH.getHandValue() < 17) {
			return true;
		} else {
			return false;
		}
	}


}
