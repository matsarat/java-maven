package com.example.java.maven.gameBlackJack;

import java.util.Stack;

public class Deck {
    private final Stack<Card> deck;

    public Deck() {
        this.deck = new Stack<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card card = new Card(suit, rank);
                deck.push(card);
            }
        }
    }

    public Stack<Card> getDeck() {
        return deck;
    }
}
