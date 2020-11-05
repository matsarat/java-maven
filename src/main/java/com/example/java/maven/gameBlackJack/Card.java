package com.example.java.maven.gameBlackJack;


public class Card {
    String suit;
    String figure;
    int value;



    public void deckBuilder() {
        Suits suits[] = Suits.values();
        Rank ranks[] = Rank.values();
        for (Suits suit : suits) {
            for (Rank rank : ranks) {
                System.out.print(suit.symbol);
                System.out.println(rank.name);


            }

        }
    }

    public enum Suits {

        CLUBS("♣"),
        DIAMONDS("♦"),
        HEARTS("♥"),
        SPADES("♠");

        private String symbol;

        Suits(String symbol) {
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

        private int value;
        private String name;

        Rank(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }

}

