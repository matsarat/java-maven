package com.example.java.maven.game2048;

public class Game {
    private final int winningNumber;
    private final Board board;

    public Game(int winningNumber, Board board) {
        this.winningNumber = winningNumber;
        this.board = board;
        board.placeTwoInitialElementsOnBoard();
    }


    public void start() {
        String playerMove = getPlayerMove();
    }

    private String getPlayerMove() {
        String userInput = UserInputProvider.getMoveFromPlayer();
        if (playerMoveIsValid(userInput)) {
            return userInput;
        }
        return getPlayerMove();
    }

    private boolean playerMoveIsValid(String userInput) {
        if (userInput.equalsIgnoreCase("a")) {
            if (board.canMoveLeft()) {
                return true;
            }
        }
        if (userInput.equalsIgnoreCase("d")) {
            if (board.canMoveRight()) {
                return true;
            }
        }
        if (userInput.equalsIgnoreCase("w")) {
            if (board.canMoveUp()) {
                return true;
            }
        }
        if (userInput.equalsIgnoreCase("s")) {
            return board.canMoveDown();
        }
        return false;
    }

    private void makeValidMove() {
        String userMove = getPlayerMove();
        if (userMove.equalsIgnoreCase("a")) {
            board.moveLeft();
            board.sumLeftOrRight();
            board.moveLeft();
        }

        if (userMove.equalsIgnoreCase("d")) {
            board.moveRight();
            board.sumLeftOrRight();
            board.moveRight();
        }

        if (userMove.equalsIgnoreCase("w")) {
            board.moveUp();
            board.sumUpOrDown();
            board.moveUp();
        }

        if (userMove.equalsIgnoreCase("s")) {
            board.moveDown();
            board.sumUpOrDown();
            board.moveDown();
        }
    }

}