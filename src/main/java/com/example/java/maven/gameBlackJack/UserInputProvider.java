package com.example.java.maven.gameBlackJack;

import java.util.Scanner;

public class UserInputProvider {
    MessagePrinter messagePrinter;

    public String getPlayersName() {
        messagePrinter.printMessage("" + '\n' +
                "What is your name? ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getPlayersDecision(String name) {
        messagePrinter.printMessage(
                "" + '\n' +
                "What is your decision, " + name + "?" + '\n' +
                "Hit (H) or stand (S)?"
        );
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}
