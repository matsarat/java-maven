package com.example.java.maven.game2048;

import java.util.Scanner;

public class UserInputProvider {
    public static int getIntFromUser(){
        while (true){
            try {
                return new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.err.println("Dej mie inta");
            }
        }
    }

}
