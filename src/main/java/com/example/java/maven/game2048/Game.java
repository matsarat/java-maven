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
            makeValidMove();
            if (board.checkIfWon(winningNumber)) {
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
            return true;
        }
        if (userInput.equalsIgnoreCase("d")) {
            return true;
        }
        if (userInput.equalsIgnoreCase("w")) {
            return true;
        }
        return userInput.equalsIgnoreCase("s");
    }

    private void makeValidMove() {
        String userInput = getPlayerMove();
        Board copyOfBoard = new Board(this.board);
        if (userInput.equalsIgnoreCase("a")) {
            board.moveLeft();
            board.sumLeft();
            board.moveLeft();
        }

        if (userInput.equalsIgnoreCase("d")) {
            board.moveRight();
            board.sumRight();
            board.moveRight();
        }

        if (userInput.equalsIgnoreCase("w")) {
            board.moveUp();
            board.sumUp();
            board.moveUp();
        }

        if (userInput.equalsIgnoreCase("s")) {
            board.moveDown();
            board.sumDown();
            board.moveDown();
        }
        if (copyOfBoard.equals(this.board)) {
            System.out.print(this.board);
            System.out.println("Invalid move!");
            makeValidMove();
        }
    }

}