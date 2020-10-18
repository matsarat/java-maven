package com.example.java.maven.game2048;

public class Game {
    private final int winningNumber;
    private final Board board;

    public Game(int winningNumber, Board board) {
        this.winningNumber = winningNumber;
        this.board = board;
        String instructions = ("INSTRUCTIONS:" + '\n' +
                "To move left   - insert a" + '\n' +
                "To move right  - insert d" + '\n' +
                "To move up     - insert w" +'\n' +
                "To move down   - insert s" + '\n'
        );
        board.placeTwoInitialElementsOnBoard();
        MessagePrinter.printMessage(instructions);
    }


    public void start() {
        while (!board.checkIfGameOver()) {
            MessagePrinter.printBoard(board);
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
            MessagePrinter.printBoard(this.board);
            MessagePrinter.printError("Invalid move!");
            makeValidMove();
        }
    }

}