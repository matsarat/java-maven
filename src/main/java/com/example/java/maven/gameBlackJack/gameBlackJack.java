package com.example.java.maven.gameBlackJack;

public class gameBlackJack {
    public static void main(String[] args) {
        UserInputProvider userInputProvider = new UserInputProvider();
        MessagePrinter messagePrinter = new MessagePrinter();
        Player player = new Player(userInputProvider.getPlayersName());
        Player croupier = new Player("Croupier");
        Deck deck = new Deck();

        Game game = new Game(player, croupier, deck, userInputProvider, messagePrinter);



    }
}
