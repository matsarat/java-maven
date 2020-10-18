package com.example.java.maven.game2048;

import java.util.Scanner;

public class UserInputProvider {

    public static String getMoveFromPlayer() {
        MessagePrinter.printMessage("" + '\n' +
                "Next move: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
