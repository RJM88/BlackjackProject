package com.skilldistillery.common.cards;

import java.util.ArrayList;
import java.util.List;

public abstract class CardHand {
	protected List<Card> cards = new ArrayList<Card>();

	public CardHand(List<Card> cards) {
		super();
		this.cards = cards;
	}

	public CardHand() {
	}
	
	public List<Card> getCards() {
		return cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void clearHand() {
		cards = new ArrayList<Card>();

	}
	public abstract int getHandValue();

	@Override
	public String toString() {
		return "Your cards: " + cards ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardHand other = (CardHand) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}
}
