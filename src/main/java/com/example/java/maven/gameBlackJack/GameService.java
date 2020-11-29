package com.example.java.maven.gameBlackJack;

import java.util.List;

public class GameService {
    private final Game game;

    public GameService(Game game) {
        this.game = game;
    }

    public void playGame(List<Player> humanPlayers){
        game.dealCards();
        game.printPlayers();
        game.printInstructions();
        for (Player player : humanPlayers) {
            game.playersDecision(player);
            game.checkIfPlayerInstantlyLostOrWon(player);
        }
        if (game.getPlayersStillInGame().size() != 0) {
            game.croupiersPlay();
            if (!game.didCroupierInstantlyLost()) {
                for (Player player : game.getPlayersStillInGame()) {
                    game.determineWinnersAndLosersAfterCroupiersPlay(player);
                }
            }
        }
    }
}
