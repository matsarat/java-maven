package com.example.java.maven.gameBlackJack;

public class Game {
    Player player = new Player(UserInputProvider.getPlayersName());
    Croupier croupier = new Croupier();
    private Deck deck;
    private final static String INSTRUCTIONS =
            ('\n' + "INSTRUCTIONS:" + '\n' +
            "To take card (hit)    - insert H" + '\n' +
            "To do nothing (stand) - insert S"
            );
    private final static String INVALID_INPUT = ("Invalid input! Please stick to instructions!");


    public Game() {
        this.deck = new Deck();
        initiateGame();
        playersDecision();
    }

    public void initiateGame() {
        player.hand.add(deck.getCard());
        croupier.hand.add(deck.getCard());
        player.hand.add(deck.getCard());
        croupier.hand.add(deck.getCard());

        MessagePrinter.printPlayer(player);
        MessagePrinter.printCroupier(croupier);

        MessagePrinter.printMessage(INSTRUCTIONS);
    }

    private boolean isPlayersDecisionValid(String userInput) {
        if (userInput.equalsIgnoreCase("H")) {
            return true;
        }
        else if (userInput.equalsIgnoreCase("S")) {
            return true;
        }
        MessagePrinter.printError(INVALID_INPUT);
        return false;
    }

    private String getPlayersDecision() {
        String userInput = UserInputProvider.getPlayersDecision(player.name);
        if (isPlayersDecisionValid(userInput)) {
            return userInput;
        }
        return getPlayersDecision();
    }

    public void playersDecision() {
        getPlayersDecision();


    }

}
