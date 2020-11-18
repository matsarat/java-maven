package com.example.java.maven.gameBlackJack;

public class Game {
    Player player = new Player(UserInputProvider.getPlayersName());
    Croupier croupier = new Croupier();
    boolean playerIsPlaying = true;
    private final Deck deck;
    private final static String INSTRUCTIONS =
            ('\n' + "INSTRUCTIONS:" + '\n' +
                    "To take card (hit)    - insert H" + '\n' +
                    "To do nothing (stand) - insert S"
            );
    private final static String INVALID_INPUT = ("Invalid input! Please stick to instructions!");
    private final String playerLost = ("No luck this time! You lost, " + player.name + "!");
    private final String playerWon = ("You won, " + player.name + ", you lucky guy!");
    private final static String CROUPIER_TURN = ('\n' + "Croupier's turn now.");
    private final static String CROUPIER_LOST = ('\n' + "Croupier lost.");
    private final static String CROUPIER_WON = ('\n' + "Croupier won.");
    private final static String TIE = ('\n' + "It's a tie!");



    public Game() {
        this.deck = new Deck();
        initiateGame();
        while (this.playerIsPlaying) {
            playersDecision();
        }
        gameFinishing();
    }

    public void initiateGame() {
        player.hand.add(deck.getCard());
        croupier.hand.add(deck.getCard());
        player.hand.add(deck.getCard());
        croupier.hand.add(deck.getCard());

        MessagePrinter.printPlayer(player);
        MessagePrinter.printCroupiersInitialHand(croupier);

        MessagePrinter.printMessage(INSTRUCTIONS);
    }

    private boolean isPlayersDecisionValid(String userInput) {
        if (userInput.equalsIgnoreCase("H")) {
            return true;
        } else if (userInput.equalsIgnoreCase("S")) {
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
        if (player.points < 21) {
            String userInput = getPlayersDecision();
            if (userInput.equalsIgnoreCase("H")) {
                player.hand.add(deck.getCard());
                MessagePrinter.printPlayer(player);
            }
        }
        this.playerIsPlaying = false;
    }

    public void gameFinishing() {
        if (player.points == 21) {
            MessagePrinter.printMessage(playerWon);
            MessagePrinter.printPlayer(player);
        } else if (player.points > 21) {
            MessagePrinter.printMessage(playerLost);
            MessagePrinter.printPlayer(player);
        } else {
            croupiersPlay();
            if (croupier.points > 21) {
                MessagePrinter.printMessage(CROUPIER_LOST);
                MessagePrinter.printCroupier(croupier);
            }
            else if (croupier.points > player.points) {
                MessagePrinter.printPlayerAndCroupier(player, croupier);
                MessagePrinter.printMessage(CROUPIER_WON);
            }
            else if (croupier.points < player.points) {
                MessagePrinter.printPlayerAndCroupier(player, croupier);
                MessagePrinter.printMessage(playerWon);
            }
            else if (croupier.points == player.points) {
                MessagePrinter.printPlayerAndCroupier(player, croupier);
                MessagePrinter.printMessage(TIE);
            }
        }
    }


    public void croupiersPlay() {
        MessagePrinter.printMessage(CROUPIER_TURN);
        while (croupier.getPoints() < 17) {
            croupier.hand.add(deck.getCard());
        }
        MessagePrinter.printCroupier(croupier);
    }
}
