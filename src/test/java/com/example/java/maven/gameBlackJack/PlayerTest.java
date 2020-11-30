package com.example.java.maven.gameBlackJack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PlayerTest {
    Player player = new Player("Januż");


    @Test
    void playerShouldHaveInitiallyZeroPoints() {
        Assertions.assertEquals(0,player.getPoints());
    }

}