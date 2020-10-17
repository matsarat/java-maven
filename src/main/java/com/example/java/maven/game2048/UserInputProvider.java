package com.example.java.maven.game2048;

import java.util.Scanner;

public class UserInputProvider {

    public static String getMoveFromPlayer() {
        System.out.println("Next move: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
