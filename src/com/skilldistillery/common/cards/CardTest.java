package com.skilldistillery.common.cards;

import java.util.Scanner;

public class CardTest {
	public static void main(String[] args) {
    Rank[] ranks = Rank.values();
    for(int i=0; i<ranks.length; i++) {
      System.out.println(ranks[i] + " " + ranks[i].getValue());
    }
//    
    for(Suit s : Suit.values()){
      System.out.println(s);
    }

		Deck deck = new Deck();
		deck.shuffle();

		Scanner kb = new Scanner(System.in);
//		System.out.println("How many cards?");
		System.out.println("Hit?");

		try {
			String num = kb.next();
			if(num.equals("N")) {
				System.out.println("OK");
			}else {
				int value = 0;
				for( int i = 0; i < 1; i++) {
					Card c = deck.dealCard();
					value += c.getValue();
					System.out.println(c);
				}
				System.out.println("your value is " + value);
			}
		} catch (Exception e) {
			System.out.println("Please enter a number.");
		}
	}
}
