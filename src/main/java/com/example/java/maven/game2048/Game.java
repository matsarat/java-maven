package com.example.java.maven.game2048;

public class Game {
    private final int winningNumber;
    private final Board board;
    private final static String INSTRUCTIONS = ("INSTRUCTIONS:" + '\n' +
            "To move left   - insert a" + '\n' +
            "To move right  - insert d" + '\n' +
            "To move up     - insert w" +'\n' +
            "To move down   - insert s" + '\n'
    );
    private final static String YOU_LOST = ("You lost! :(");
    private final static String YOU_WON = ("You won! :D");
    private final static String INVALID_MOVE = ("Invalid move!");


    public Game(int winningNumber, Board board) {
        this.winningNumber = winningNumber;
        this.board = board;
        board.placeTwoInitialElementsOnBoard();
        MessagePrinter.printMessage(INSTRUCTIONS);
    }


    public void start() {
        while (isNotOver()) {
            makeValidMove();
            if (playerWon()) {
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
        if (isMoveLeft(userInput)) {
            return true;
        }
        if (isMoveRight(userInput)) {
            return true;
        }
        if (isMoveUp(userInput)) {
            return true;
        }
        return isMoveDown(userInput);
    }


    private void makeValidMove() {
        MessagePrinter.printBoard(this.board);
        String userInput = getPlayerMove();
        Board copyOfBoard = new Board(this.board);
        if (isMoveLeft(userInput)) {
            board.sortBoardLeft();
            board.sumLeft();
            board.sortBoardLeft();
        }

        if (isMoveRight(userInput)) {
            board.sortBoardRight();
            board.sumRight();
            board.sortBoardRight();
        }

        if (isMoveUp(userInput)) {
            board.sortBoardUp();
            board.sumUp();
            board.sortBoardUp();
        }

        if (isMoveDown(userInput)) {
            board.sortBoardDown();
            board.sumDown();
            board.sortBoardDown();
        }
        if (copyOfBoard.equals(this.board)) {
            MessagePrinter.printError(INVALID_MOVE);
            makeValidMove();
        }
    }

    private boolean isMoveUp(String userInput) {
        return userInput.equalsIgnoreCase("w");
    }

    private boolean isMoveDown(String userInput) {
        return userInput.equalsIgnoreCase("s");
    }

    private boolean isMoveRight(String userInput) {
        return userInput.equalsIgnoreCase("d");
    }

    private boolean isMoveLeft(String userInput) {
        return userInput.equalsIgnoreCase("a");
    }

    private boolean isNotOver() {
        int zeroValuesOnBoard =board.getFieldsWithZeroValue().size();
        if (zeroValuesOnBoard > 0) {
            return true;
        }
        if (board.hasSimilarNeighboringFields()) {
            return true;
        }
        MessagePrinter.printMessage(YOU_LOST);
        return false;
    }

    private boolean playerWon() {
        if (board.hasWinningNumber(winningNumber)) {
            MessagePrinter.printMessage(YOU_WON);
            MessagePrinter.printBoard(board);
            return true;
        }
        return false;
    }

}