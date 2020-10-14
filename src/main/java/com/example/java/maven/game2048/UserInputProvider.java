package com.example.java.maven.game2048;

import java.util.Scanner;

public class UserInputProvider {
    public static String getIntFromUser(){
        while (true){
            try {
                return new Scanner(System.in).next();
            } catch (Exception e) {
                System.err.println("Dej mie inta");
            }
        }
    }
    public static String getMoveFromPlayer(){
        Scanner scanner = new Scanner(System.in);
        String move = scanner.nextLine();
        return move;
    }
}
