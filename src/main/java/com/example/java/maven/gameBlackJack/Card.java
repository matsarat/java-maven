package com.example.java.maven.gameBlackJack;

public class Card {
    Suit suit;
    Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return suit.symbol + rank.figure;
    }

    public enum Suit {

        CLUB("♣"),
        DIAMOND("♦"),
        HEART("♥"),
        SPADE("♠");

        private final String symbol;

        Suit(String symbol) {
            this.symbol = symbol;
        }
    }

    public enum Rank {
        ACE(1, "A"),
        TWO(2, "2"),
        THREE(3, "3"),
        FOUR(4, "4"),
        FIVE(5, "5"),
        SIX(6, "6"),
        SEVEN(7, "7"),
        EIGHT(8, "8"),
        NINE(9, "9"),
        TEN(10, "10"),
        JACK(10, "J"),
        QUEEN(10, "Q"),
        KING(10, "K");

        private final int value;
        private final String figure;

        Rank(int value, String figure) {
            this.value = value;
            this.figure = figure;
        }

        public int getValue() {
            return value;
        }

        public String getFigure() {
            return figure;
        }
    }
}

