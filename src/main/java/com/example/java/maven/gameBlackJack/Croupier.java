package com.example.java.maven.gameBlackJack;

import java.util.ArrayList;

public class Croupier {
    String name;
    ArrayList<Card> hand;
    int points;

    public Croupier() {
        this.name = "Croupier";
        this.hand = new ArrayList<>();
        this.points = 0;
    }

    public int getPoints() {
        for (Card card : hand) {
            points += card.rank.getValue();
        }
        return points;
    }

    @Override
    public String toString() {
        return name + " " + hand.get(0);
    }

}
