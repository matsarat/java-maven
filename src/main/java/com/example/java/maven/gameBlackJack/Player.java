package com.example.java.maven.gameBlackJack;

import java.util.Stack;

public class Player {
    String name;
    Stack<Card> hand;
    int points;

    public Player(String name, Stack<Card> hand, int points) {
        this.name = name;
        this.hand = hand;
        this.points = points;
    }
}
