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
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class GameTest {
    private final static String TIE = ('\n' + "It's a tie!");
    private final static String CROUPIER_TURN = ('\n' + "Croupier's turn now.");
    Game game;
    List<Player> playersStillInGame = new ArrayList<>();
    List<Player> humanPlayers = List.of(Mockito.mock(Player.class), Mockito.mock(Player.class));
    UserInputProvider userInputProvider = Mockito.mock(UserInputProvider.class);
    MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);
    Player croupier = Mockito.mock(Player.class);
    Deck deck = new Deck();

    @BeforeEach
    void setUp() {

        this.game = new Game(humanPlayers, playersStillInGame, croupier, deck, userInputProvider, messagePrinter);
    }

    @Test
    void shouldReturnAllPlayersList() {

//        when
        game.makePlayerList();

//        then
        assertThat(game.makePlayerList().size()).isEqualTo(3);
    }

    @Test
    void shouldDealCards() {
        List<Player> allPlayersList = game.makePlayerList();
        List<Card> player1Hand = new ArrayList<>();
        List<Card> player2Hand = new ArrayList<>();
        List<Card> player3Hand = new ArrayList<>();

//        given

        given(allPlayersList.get(0).getHand()).willReturn(player1Hand);
        given(allPlayersList.get(1).getHand()).willReturn(player2Hand);
        given(allPlayersList.get(2).getHand()).willReturn(player3Hand);

//        when
        game.dealCards();

//        then
        assertThat(player1Hand.size()).isEqualTo(2);
        assertThat(player2Hand.size()).isEqualTo(2);
        assertThat(player3Hand.size()).isEqualTo(2);

    }


    @Test
    void shouldInvokePrintingPlayerAndCroupiersInitialHand() {
        Player player = humanPlayers.get(0);
        Player player2 = humanPlayers.get(1);

//        when
        game.printPlayers();

//        then

        then(messagePrinter)
                .should(times(1))
                .printCroupiersInitialHand(croupier);
        then(messagePrinter)
                .should(times(1))
                .printPlayer(player);
        then(messagePrinter)
                .should(times(1))
                .printPlayer(player2);
    }


    @Test
    void shouldSetIsPlayingToFalseWhenPlayerHasOver20Points() {
        Player player = humanPlayers.get(0);

//        given
        given(player.getPoints()).willReturn(21);
        given(player.isPlaying()).willReturn(true).willReturn(false);
//        when
        game.playersDecision(player);

//        then

        then(player)
                .should(times(1))
                .getPoints();
        then(player)
                .should(times(1))
                .setPlaying(false);
    }


    @Test
    void shouldSetIsPlayingToFalseWhenPlayerHasLessThan21PointsAndDecisionIsDifferentThanHit() {
        String playerName = "Januż";
        Player player = humanPlayers.get(0);

//        given
        given(player.isPlaying())
                .willReturn(true)
                .willReturn(false);
        given(player.getName()).willReturn(playerName);
        given(player.getPoints()).willReturn(20);
        given(userInputProvider
                .getPlayersDecision(eq(playerName)))
                .willReturn("S");

//        when
        game.playersDecision(player);

//        then

        then(player)
                .should(times(1))
                .getPoints();
        then(userInputProvider)
                .should(times(1))
                .getPlayersDecision(eq(playerName));
        then(player)
                .should(times(1))
                .setPlaying(false);
    }

    @Test
    void shouldAddCardToPlayersHandAndPrintPlayerIfDecisionIsHit() {
        String playerName = "Januż";
        List<Card> playerHand = new ArrayList<>();
        Player player = humanPlayers.get(0);


//        given
        given(player.isPlaying())
                .willReturn(true)
                .willReturn(false);
        given(player.getName()).willReturn(playerName);
        given(player.getPoints()).willReturn(10);
        given(userInputProvider
                .getPlayersDecision(eq(playerName)))
                .willReturn("H");
        given(player.getHand())
                .willReturn(playerHand);


//        when
        game.playersDecision(player);

//        then
        then(player)
                .should(times(1))
                .getPoints();
        then(player)
                .should(times(1))
                .getHand();
        then(userInputProvider)
                .should(times(1))
                .getPlayersDecision(eq(playerName));
        then(messagePrinter)
                .should(times(1))
                .printPlayer(player);

        assertThat(playerHand.size()).isEqualTo(1);
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
        then(messagePrinter)
                .should(times(1))
                .printMessage(CROUPIER_TURN);
    }


    @Test
    void shouldAddCardToCroupiersHand() {
        List<Card> croupierHand = new ArrayList<>();

//        given
        given(croupier.getPoints()).willReturn(16).willReturn(17);
        given(croupier.getHand()).willReturn(croupierHand);

//        when
        game.croupiersPlay();

//        then
        assertThat(croupier.getHand()).hasSize(1);
        then(messagePrinter)
                .should(times(1))
                .printMessage(CROUPIER_TURN);
    }

    @Test
    void printThatPlayerInstantlyWon() {
        Player player = humanPlayers.get(0);

//        given
        given(player.getPoints()).willReturn(21);

//        when
        game.checkIfPlayerInstantlyLostOrWon(player);

//        then
        then(messagePrinter)
                .should(times(1))
                .printMessage(game.playerWonMessage(player));
        then(messagePrinter)
                .should(times(1))
                .printMessage(game.playerWonMessage(player));
    }

    @Test
    void printThatPlayerInstantlyLost() {
        Player player = humanPlayers.get(0);

//        given
        given(player.getPoints()).willReturn(22);

//        when
        game.checkIfPlayerInstantlyLostOrWon(player);

//        then
        then(messagePrinter)
                .should(times(1))
                .printMessage(game.playerInstantlyLostMessage(player));
    }

    @Test
    void shouldAddPlayerToListOfPlayersStillInGame() {
        Player player = humanPlayers.get(0);

//        given
        given(player.getPoints()).willReturn(20);

//        when
        game.checkIfPlayerInstantlyLostOrWon(player);

//        then
        assertThat(playersStillInGame.size()).isEqualTo(1);
    }


    @Test
    void shouldPrintThatCroupierInstantlyLostAndReturnTrue() {

//        given
        given(croupier.getPoints()).willReturn(22);

//        when
        game.didCroupierInstantlyLost();

//        then
        then(messagePrinter)
                .should(times(1))
                .printPlayer(croupier);
        then(messagePrinter)
                .should(times(1))
                .printMessage(game.croupierInstantlyLostMessage());
        assertThat(game.didCroupierInstantlyLost()).isTrue();
    }

    @Test
    void shouldReturnFalseIfCroupierDidNotInstantlyLost() {

//        given
        given(croupier.getPoints()).willReturn(17);

//        when
        game.didCroupierInstantlyLost();

//        then
        assertThat(game.didCroupierInstantlyLost()).isFalse();
    }



    @Test
    void shouldPrintThatCroupierWonByPointsDifference() {
        Player player = humanPlayers.get(0);

//        given
        given(player.getPoints()).willReturn(15);
        given(croupier.getPoints()).willReturn(20);

//        when
        game.determineWinnersAndLosersAfterCroupiersPlay(player);

//        then
        then(messagePrinter)
                .should(times(1))
                .printPlayerAndCroupier(player, croupier);
        then(messagePrinter)
                .should(times(1))
                .printMessage(game.playerWonMessage(croupier));
    }

    @Test
    void shouldPrintThatPlayerWonByPointsDifference() {
        Player player = humanPlayers.get(0);

//        given
        given(player.getPoints()).willReturn(20);
        given(croupier.getPoints()).willReturn(17);

//        when
        game.determineWinnersAndLosersAfterCroupiersPlay(player);

//        then
        then(messagePrinter)
                .should(times(1))
                .printPlayerAndCroupier(player, croupier);
        then(messagePrinter)
                .should(times(1))
                .printMessage(game.playerWonMessage(player));
    }

    @Test
    void shouldFinishGameAndPrintThatGameFinishedWithATie() {
        Player player = humanPlayers.get(0);

//        given
        given(player.getPoints()).willReturn(20);
        given(croupier.getPoints()).willReturn(20);

//        when
        game.determineWinnersAndLosersAfterCroupiersPlay(player);

//        then
        then(messagePrinter)
                .should(times(1))
                .printPlayerAndCroupier(player, croupier);
        then(messagePrinter)
                .should(times(1))
                .printMessage(TIE);
    }
}