package com.example.java.maven.gameBlackJack;

public class gameBlackJack {
    public static void main(String[] args) {
        Player player = new Player(UserInputProvider.getPlayersName());
        Player croupier = new Player("Croupier");
        Deck deck = new Deck();

        Game game = new Game(player, croupier, deck);



    }
}
