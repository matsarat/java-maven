package com.example.java.maven.gameBlackJack;

public class Game {
    private Deck deck;
    private final static String INSTRUCTIONS =
            ('\n' + "INSTRUCTIONS:" + '\n' +
            "To take card   - insert H" + '\n' +
            "To stand       - insert S" + '\n' +
            '\n'
            );


    public Game() {
        this.deck = new Deck();
        initiateGame();
    }

    public void initiateGame() {
        Player player = new Player(UserInputProvider.getPlayersName());
        Croupier croupier = new Croupier();


        player.hand.add(deck.getCard());
        croupier.hand.add(deck.getCard());
        player.hand.add(deck.getCard());
        croupier.hand.add(deck.getCard());

        MessagePrinter.printPlayer(player);
        MessagePrinter.printCroupier(croupier);

        MessagePrinter.printMessage(INSTRUCTIONS);


    }
}
