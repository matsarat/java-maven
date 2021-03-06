package com.example.java.maven.gameBlackJack;

import java.util.ArrayList;
import java.util.List;

public class gameBlackJack {
    public static void main(String[] args) {
        MessagePrinter messagePrinter = new MessagePrinter();
        UserInputProvider userInputProvider = new UserInputProvider(messagePrinter);
        int numberOfPlayers = userInputProvider.getNumberOfPlayers();
        List<Player> playersStillInGame = new ArrayList<>();
        List<Player> humanPlayers = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(userInputProvider.getPlayersName());
            humanPlayers.add(player);
        }
        Player croupier = new Player("Croupier");
        Deck deck = new Deck();

        Game game = new Game(humanPlayers, playersStillInGame, croupier, deck, userInputProvider, messagePrinter);
        GameService gameService = new GameService(game);
        gameService.playGame(humanPlayers);
    }
}
