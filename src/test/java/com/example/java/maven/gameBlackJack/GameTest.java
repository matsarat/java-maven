package com.example.java.maven.gameBlackJack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameTest {
    Game game;
    UserInputProvider userInputProvider = Mockito.mock(UserInputProvider.class);
    MessagePrinter messagePrinter = new MessagePrinter();
    Player player = Mockito.mock(Player.class);
    Player croupier = Mockito.mock(Player.class);
    Deck deck = new Deck();

    @BeforeEach
    void setUp() {

        this.game = new Game(player, croupier, deck, userInputProvider, messagePrinter);
    }

    @Test
    void shouldDealCards() {

//        given
        List<Card> playerHand = new ArrayList<>();
        List<Card> croupierHand = new ArrayList<>();
        given(croupier.getHand())
                .willReturn(croupierHand);
        given(player.getHand())
                .willReturn(playerHand);

//        when
        game.dealCards();

//        then
        assertThat(playerHand).hasSize(2);
        assertThat(croupierHand).hasSize(2);

    }

    @Test
    void shouldSetPlayerIsPlayingToFalseWhenPlayerHasOver20Points() {
//        given

        given(player.getPoints()).willReturn(21);
//        when
        game.playersDecision();

//        then
        assertThat(game.playerIsPlaying).isFalse();
    }

    @Test
    void shouldSetPlayersPlayingToFalseWhenPlayerHasLessThan21PointsAndDecisionIsDifferentThanHit() {
        String playerName = "Januż";
//        given
        given(player.getName()).willReturn(playerName);
        given(player.getPoints()).willReturn(20);
        given(userInputProvider
                .getPlayersDecision(eq(playerName)))
                .willReturn("S");

//        when
        game.playersDecision();

//        then
        assertThat(game.playerIsPlaying).isFalse();
        verify(player, times(1)).getName();
        verify(player, times(1)).getPoints();
        verify(userInputProvider, times(1))
                .getPlayersDecision(eq(playerName));
    }

    @Test
    void shouldAddCardToPlayersHandAndPrintPlayerIfDecisionIsHit() {
        String playerName = "Januż";
        List<Card> playerHand = new ArrayList<>();

//        given
        given(player.getName()).willReturn(playerName);
        given(player.getPoints()).willReturn(10);
        given(userInputProvider
                .getPlayersDecision(eq(playerName)))
                .willReturn("H");
        given(player.getHand())
                .willReturn(playerHand);


//        when
        game.playersDecision();

//        then
        verify(player, times(1)).getHand();
        verify(player, times(1)).getPoints();
        verify(userInputProvider, times(1))
                .getPlayersDecision(eq(playerName));
        assertThat(player.getHand().size()).isEqualTo(1);
        assertThat(game.playerIsPlaying).isTrue();

    }
}