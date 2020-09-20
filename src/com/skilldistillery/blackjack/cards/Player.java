package com.skilldistillery.blackjack.cards;

import com.skilldistillery.common.cards.Card;

public class Player {
	protected BlackJackHand bjH = new BlackJackHand();

	public Player() {
		super();
	}

	public void takeCard(Card card) {
		bjH.addCard(card);
	}

	public int getPlayerHandValue() {

		return bjH.getHandValue();
	}

	public boolean blackJackCheck() {
		if (bjH.getHandValue() == 21) {
			return true;

		}
		return false;
	}

	public BlackJackHand getBjH() {
		return bjH;
	}

	public void setBjH(BlackJackHand bjH) {
		this.bjH = bjH;
	}

	@Override
	public String toString() {
		return " = " + bjH + "]";
	}

}
