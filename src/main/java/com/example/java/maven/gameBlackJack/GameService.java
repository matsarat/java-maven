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
        }
        game.gameFinishing();
    }
}
