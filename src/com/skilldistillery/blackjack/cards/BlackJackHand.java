package com.skilldistillery.blackjack.cards;

import com.skilldistillery.common.cards.Card;
import com.skilldistillery.common.cards.CardHand;

public class BlackJackHand extends CardHand {

	public BlackJackHand() {
		super();

	}

	@Override
	public int getHandValue() {
		int totalValue = 0;
		for (Card card : cards) {
			totalValue += card.getValue();
		}
		return totalValue;
	}

	public boolean isBust() {
		if (getHandValue() > 21) {
			return true;

		}
		return false;

	}
}
