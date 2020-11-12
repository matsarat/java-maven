package com.example.java.maven.gameBlackJack;

public class MessagePrinter {

    private MessagePrinter() {
    }

    public static void printMessage(String messageToPrint) {
        System.out.println(messageToPrint);
    }

    public static void printError(String errorToPrint){
        System.err.println(errorToPrint);
    }
}
