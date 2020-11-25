package com.example.java.maven.gameBlackJack;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    public Deck() {
        this.cards = new Stack<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card card = new Card(suit, rank);
                cards.push(card);
            }
        }
        Collections.shuffle(cards);
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public Card getCard() {
        return cards.pop();

    }

    @Override
    public String toString() {
        return "Deck: " + cards;
    }
}
