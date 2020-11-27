package com.example.java.maven.gameBlackJack;

import java.util.ArrayList;
import java.util.List;

public class gameBlackJack {
    public static void main(String[] args) {
        UserInputProvider userInputProvider = new UserInputProvider();
        MessagePrinter messagePrinter = new MessagePrinter();
        int numberOfPlayers = userInputProvider.getNumberOfPlayers();
        List<Player> humanPlayers = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(userInputProvider.getPlayersName());
            humanPlayers.add(player);
        }
        Player croupier = new Player("Croupier");
        Deck deck = new Deck();

        Game game = new Game(humanPlayers, croupier, deck, userInputProvider, messagePrinter);
        GameService gameService = new GameService(game);
        gameService.playGame(humanPlayers);
    }
}
