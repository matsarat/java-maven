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

    public static void printPlayer(Player player) {
        System.out.println(player);
    }

    public static void printCroupier(Croupier croupier) {
        System.out.println(croupier);
    }

    public static void printCroupiersInitialHand(Croupier croupier) {
        System.out.println("Croupier " + croupier.getHand().get(0));
    }

    public static void printPlayerAndCroupier(Player player, Croupier croupier) {
        System.out.print(player);
        System.out.print(" VERSUS ");
        System.out.print(croupier);
    }

}
