package com.example.java.maven.game2048;

public class MessagePrinter {

    private MessagePrinter() {
    }

    public static void printMessage(String messageToPrint) {
        System.out.println(messageToPrint);
    }

    public static void printError(String errorToPrint){
        System.err.println(errorToPrint);
    }

    public static void printBoard(Board boardToPrint){
        System.out.print(boardToPrint);
    }
}
