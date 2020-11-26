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
    private final static String TIE = ('\n' + "It's a tie!");
    private final static String CROUPIER_TURN = ('\n' + "Croupier's turn now.");
    Game game;
    UserInputProvider userInputProvider = Mockito.mock(UserInputProvider.class);
    MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);
    Player player = Mockito.mock(Player.class);
    Player croupier = new Player("Croupier");
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
        verify(messagePrinter, times(1))
                .printPlayer(player);
        assertThat(player.getHand().size()).isEqualTo(1);
        assertThat(game.playerIsPlaying).isTrue();

    }

    @Test
    void shouldNotAddCardToCroupiersHand() {
        List<Card> croupierHand = new ArrayList<>();

//        given
        given(croupier.getPoints()).willReturn(17);
        given(croupier.getHand()).willReturn(croupierHand);

//        when
        game.croupiersPlay();

//        then
        assertThat(croupierHand).hasSize(0);
        verify(messagePrinter, times(1)).printMessage(CROUPIER_TURN);
    }

    @Test
    void shouldAddCardToCroupiersHand() {
        Card card1 = new Card(Card.Suit.SPADE, Card.Rank.KING);
        Card card2 = new Card(Card.Suit.SPADE, Card.Rank.SIX);
        croupier.getHand().add(card1);
        croupier.getHand().add(card2);

//        given

//        when
        game.croupiersPlay();

//        then
        assertThat(croupier.getHand()).hasSize(3);
        verify(messagePrinter, times(1))
                .printMessage(CROUPIER_TURN);
    }

    @Test
    void shouldFinishGameAndPrintThatPlayerInstantlyWon() {

//        given
        given(player.getPoints()).willReturn(21);

//        when
        game.gameFinishing();

//        then
        verify(messagePrinter, times(1))
                .printMessage(game.playerWonMessage(player));
        verify(messagePrinter, times(1))
                .printPlayer(player);
    }

    @Test
    void shouldFinishGameAndPrintThatCroupierInstantlyWon() {

//        given
        given(player.getPoints()).willReturn(22);

//        when
        game.gameFinishing();

//        then
        verify(messagePrinter, times(1))
                .printMessage(game.playerWonMessage(croupier));
    }

    @Test
    void shouldFinishGameAndPrintThatPlayerWonAfterCroupiersTurn() {

//        given
        given(croupier.getPoints()).willReturn(15);
        given(croupier.getPoints()).willReturn(22);

//        when
        game.gameFinishing();

//        then
        verify(messagePrinter, times(1))
                .printMessage(game.playerWonMessage(player));
        verify(messagePrinter, times(1))
                .printPlayer(croupier);
    }

    @Test
    void shouldFinishGameAndPrintThatCroupierWonByPointsDifference() {

//        given
        given(player.getPoints()).willReturn(15);
        given(croupier.getPoints()).willReturn(20);

//        when
        game.gameFinishing();

//        then
        verify(messagePrinter, times(1))
                .printPlayerAndCroupier(player, croupier);
        verify(messagePrinter, times(1))
                .printMessage(game.playerWonMessage(croupier));
    }

    @Test
    void shouldFinishGameAndPrintThatPlayerWonByPointsDifference() {

//        given
        given(player.getPoints()).willReturn(20);
        given(croupier.getPoints()).willReturn(17);

//        when
        game.gameFinishing();

//        then
        verify(messagePrinter, times(1))
                .printPlayerAndCroupier(player, croupier);
        verify(messagePrinter, times(1))
                .printMessage(game.playerWonMessage(player));
    }

    @Test
    void shouldFinishGameAndPrintThatGameFinishedWithATie() {

//        given
        given(player.getPoints()).willReturn(20);
        given(croupier.getPoints()).willReturn(20);

//        when
        game.gameFinishing();

//        then
        verify(messagePrinter, times(1))
                .printPlayerAndCroupier(player, croupier);
        verify(messagePrinter, times(1))
                .printMessage(TIE);
    }
}