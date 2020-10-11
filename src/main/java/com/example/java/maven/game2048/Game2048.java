package com.example.java.maven.game2048;

public class Game2048 {
    public static void main(String[] args) {
        Board board = new Board(4, 4);
        Game game = new Game(2048, board);
        game.start();
    }
}
