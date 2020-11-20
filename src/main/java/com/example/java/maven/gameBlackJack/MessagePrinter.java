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


    public static void printCroupiersInitialHand(Player croupier) {
        System.out.println("Croupier " + croupier.getHand().get(0));
    }

    public static void printPlayerAndCroupier(Player player, Player croupier) {
        System.out.print(player);
        System.out.print("  VERSUS  ");
        System.out.println(croupier);
    }
}
