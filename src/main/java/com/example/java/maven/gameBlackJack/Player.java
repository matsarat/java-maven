package com.example.java.maven.gameBlackJack;

import java.util.ArrayList;

public class Player {
    String name;
    ArrayList<Card> hand;
    int points;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.points = getPoints();
    }

    public void addCardToPlayersHand(Card card) {
        hand.add(card);
    }

    public int getPoints() {
        points = 0;
        for (Card card : hand) {
            points += card.rank.getValue();
        }
        return points;

    }

    @Override
    public String toString() {
        return name + " " + hand + "  Points: " + getPoints();
    }
}
