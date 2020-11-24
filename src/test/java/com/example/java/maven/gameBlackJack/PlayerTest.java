package com.example.java.maven.gameBlackJack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PlayerTest {
    Player player = new Player("Januż");
    Deck deck = new Deck();
    private static final int NUMBER_OF_INITIAL_CARDS = 2;


    @Test
    void playerShouldHaveInitiallyZeroPoints() {
        Assertions.assertEquals(0,player.getPoints());
    }


    @Test
    void playerShouldHaveMoreThanZeroPointsAfterDealingCards() {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            player.getHand().add(deck.getCard());
        }
        Assertions.assertNotEquals(0, player.getPoints());
    }

    @Test
    void playerShouldHaveTwoCardsOnHand() {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            player.getHand().add(deck.getCard());
        }
        Assertions.assertEquals(2, player.getHand().size());
    }
}