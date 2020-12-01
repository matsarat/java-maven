package com.example.java.maven.gameBlackJack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final ArrayList<Card> hand;
    private boolean isPlaying;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.isPlaying = true;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
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
        return String.format("%s %s Points: %s", name, hand, getPoints());
    }
}
