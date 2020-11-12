package com.example.java.maven.gameBlackJack;

import java.util.Scanner;

public class UserInputProvider {

    public static String getMoveFromPlayer() {
        MessagePrinter.printMessage("" + '\n' +
                "Next move: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String getPlayersName() {
        MessagePrinter.printMessage("" + '\n' +
                "What is your name? ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }
}
