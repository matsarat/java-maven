package com.example.java.maven.gameBlackJack;

import java.util.Stack;

public class Player {
    String name;
    Stack<Card> hand;
    int points;

    public Player(String name) {
        this.name = name;
    }

    public void addCardToPlayersHand(Card card) {
        hand.push(card);
    }

    public int getPoints() {
        return points;
    }
}
