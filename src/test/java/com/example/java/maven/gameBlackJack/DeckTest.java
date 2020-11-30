package com.example.java.maven.gameBlackJack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck = new Deck();

    @Test
    void deckShouldHaveFiftyTwoCards() {
            Assertions.assertEquals(52, deck.getCards().size());
    }
}

