package com.example.java.maven.gameBlackJack;

import java.util.Scanner;

public class UserInputProvider {

    public static String getPlayersName() {
        MessagePrinter.printMessage("" + '\n' +
                "What is your name? ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String getPlayersDecision(String name) {
        MessagePrinter.printMessage(
                "" + '\n' +
                "What is your decision, " + name + "?" + '\n' +
                "Hit (H) or stand (S)?"
        );
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}
