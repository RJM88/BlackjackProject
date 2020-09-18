package com.skilldistillery.common.cards;

public enum Suit {
	HEARTS("\u2665"), SPADES("\u2660"), CLUBS("\u2663"), DIAMONDS("\u2666");
//	HEARTS("Hearts"), SPADES("Spades"), CLUBS("Clubs"), DIAMONDS("Diamonds");

	final private String suit;

	Suit(String suit) {
		this.suit = suit;
	}

	@Override
	public String toString() {
		return suit;
	}

	public String getName() {
		return suit;
	}
}
