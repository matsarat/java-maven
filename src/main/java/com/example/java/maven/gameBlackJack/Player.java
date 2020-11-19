package com.example.java.maven.gameBlackJack;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        int points = 0;
        for (Card card : hand) {
            points += card.getRank().getValue();
        }
        return points;

    }

    @Override
    public String toString() {
        return name + " " + hand + "  Points: " + getPoints();
    }
}
