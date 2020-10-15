package com.example.java.maven.game2048;

public class Game {
    private final int winningNumber;
    private final Board board;

    public Game(int winningNumber, Board board) {
        this.winningNumber = winningNumber;
        this.board = board;
        board.placeTwoInitialElementsOnBoard();
        MessagePrinter.printInstructionsBeforeGame();
    }


    public void start() {
        while (!board.checkIfGameOver()) {
            System.out.println(board);
            String userInput = getPlayerMove();
            makeValidMove(userInput);
            if (board.checkIfWon()) {
                break;
            }
            board.placeNextElementOnBoard();
        }
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
            if (board.canMoveDown()) {
                return true;
            }
        }
        return false;
    }

    private void makeValidMove(String userInput) {
        if (userInput.equalsIgnoreCase("a")) {
            board.moveLeft();
            board.sumLeftOrRight();
            board.moveLeft();
        }

        if (userInput.equalsIgnoreCase("d")) {
            board.moveRight();
            board.sumLeftOrRight();
            board.moveRight();
        }

        if (userInput.equalsIgnoreCase("w")) {
            board.moveUp();
            board.sumUpOrDown();
            board.moveUp();
        }

        if (userInput.equalsIgnoreCase("s")) {
            board.moveDown();
            board.sumUpOrDown();
            board.moveDown();
        }
    }

}