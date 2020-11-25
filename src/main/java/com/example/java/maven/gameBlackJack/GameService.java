package com.example.java.maven.gameBlackJack;

public class GameService {
    private final Game game;

    public GameService(Game game) {
        this.game = game;
    }

    public void playGame(){
        game.dealCards();
        game.printPlayers();
        game.printInstructions();
        while (this.game.playerIsPlaying) {
            game.playersDecision();
        }
        game.gameFinishing();


    }
}
