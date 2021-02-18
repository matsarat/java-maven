package com.example.java.maven.gameBlackJack;

import java.util.Scanner;

public class UserInputProvider {
    MessagePrinter messagePrinter;


    public UserInputProvider(MessagePrinter messagePrinter) {
        this.messagePrinter = messagePrinter;
    }
    
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

    public int getNumberOfPlayers() {
        int numberOfPlayers;
        messagePrinter.printMessage(
                """

                        How many players will be playing?
                        Game is for maximum 6 players.
                        """
        );
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            messagePrinter.printError("Number of players must be an integer between 1 and 6!");
            return getNumberOfPlayers();
        }
        else if (scanner.hasNextInt()) {
            numberOfPlayers = scanner.nextInt();
            if (numberOfPlayers > 0 && numberOfPlayers < 7) {
                return numberOfPlayers;
            }
            else {
                messagePrinter.printError("Number of players must be an integer between 1 and 6!");
                return  getNumberOfPlayers();
            }
        }
        return 0;
    }
}
