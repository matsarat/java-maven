package com.example.java.maven.gameBlackJack;

public class MessagePrinter {

    public MessagePrinter() {
    }

    public void printMessage(String messageToPrint) {
        System.out.println(messageToPrint);
    }

    public void printError(String errorToPrint){
        System.err.println(errorToPrint);
    }

    public void printPlayer(Player player) {
        System.out.println(player);
    }


    public void printCroupiersInitialHand(Player croupier) {
        System.out.println("Croupier " + croupier.getHand().get(0));
    }

    public void printPlayerAndCroupier(Player player, Player croupier) {
        System.out.print(player);
        System.out.print("  VERSUS  ");
        System.out.println(croupier);
    }
}
