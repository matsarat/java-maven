package com.example.java.maven.gameBlackJack;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> playersStillInGame;
    private final UserInputProvider userInputProvider;
    private final MessagePrinter messagePrinter;
    private final List<Player> humanPlayers;
    private final Player croupier;
    private final Deck deck;
    private final static String INSTRUCTIONS ="""
            
            INSTRUCTIONS:
            To take card (hit)    - insert H"
            To do nothing (stand) - insert S""";
    private final static String CROUPIER_TURN = ('\n' + "Croupier's turn now.");
    private final static String TIE = ('\n' + "It's a tie!");
    private static final int NUMBER_OF_INITIAL_CARDS = 2;



    public Game(List<Player> humanPlayers,
                List<Player> playersStillInGame,
                Player croupier,
                Deck deck,
                UserInputProvider userInputProvider,
                MessagePrinter messagePrinter) {
        this.playersStillInGame = playersStillInGame;
        this.humanPlayers = humanPlayers;
        this.croupier = croupier;
        this.deck = deck;
        this.userInputProvider = userInputProvider;
        this.messagePrinter = messagePrinter;
    }

    public List<Player> getPlayersStillInGame() {
        return playersStillInGame;
    }

    public void printInstructions() {
        messagePrinter.printMessage(INSTRUCTIONS);
    }

    public void printPlayers() {
        for (Player player : humanPlayers) {
            messagePrinter.printPlayer(player);
        }
        messagePrinter.printCroupiersInitialHand(croupier);
    }

    private List<Player> makePlayerList() {
        List<Player> allPlayersList = new ArrayList<>();
        allPlayersList.addAll(humanPlayers);
        allPlayersList.add(croupier);
        return allPlayersList;
    }

    public void dealCards() {
        for (Player player : makePlayerList()) {
            for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
                player.getHand().add(deck.getCard());
            }
        }
    }

    private enum PlayersDecision {

        HIT("H"),
        STAND("S")
        ;
        private final String decision;

        PlayersDecision(String decision) {
            this.decision = decision;
        }

        public String getDecision() {
            return decision;
        }

        public static List<String> getAllDecisions(){
            List<String> allDecisions = new ArrayList<>();
            for (PlayersDecision decision : PlayersDecision.values()) {
                allDecisions.add(decision.getDecision());
            }
            return allDecisions;
        }

        public static PlayersDecision matchUserInput(String userInput) {
            for (PlayersDecision decision : PlayersDecision.values()) {
                if (decision.getDecision().equalsIgnoreCase(userInput)) {
                    return decision;
                }
            }
            throw new IllegalArgumentException(String.format("Invalid user input: %s. Valid input values: %S", userInput, getAllDecisions()));
        }
    }

    public PlayersDecision getPlayersDecision(Player player) {
        String userInput = userInputProvider.getPlayersDecision(player.getName());
        try {
            return PlayersDecision.matchUserInput(userInput.toUpperCase());
        } catch (IllegalArgumentException exception) {
            messagePrinter.printError(exception.getMessage());
            return getPlayersDecision(player);
        }
    }

    public void playersDecision(Player player) {
            while (player.isPlaying) {
                if (player.getPoints() < 21) {
                    PlayersDecision playersDecision = getPlayersDecision(player);
                    if (playersDecision == PlayersDecision.HIT) {
                        player.getHand().add(deck.getCard());
                        messagePrinter.printPlayer(player);
                    } else {
                        player.isPlaying = false;
                    }
                }
                else {
                    player.isPlaying = false;
                }
            }
    }

    public void checkIfPlayerInstantlyLostOrWon(Player player) {
        if (player.getPoints() == 21) {
            messagePrinter.printMessage(playerWonMessage(player));
            messagePrinter.printPlayer(player);
        } else if (player.getPoints() > 21) {
            messagePrinter.printMessage(playerWonMessage(croupier));
        }
        else {
            this.playersStillInGame.add(player);
        }
    }

    public void determineWinnersAndLosersAfterCroupiersPlay(Player player) {
        if (croupier.getPoints() > 21) {
            messagePrinter.printMessage(playerWonMessage(player));
            messagePrinter.printPlayer(croupier);
        } else if (croupier.getPoints() > player.getPoints()) {
            messagePrinter.printPlayerAndCroupier(player, croupier);
            messagePrinter.printMessage(playerWonMessage(croupier));
        } else if (croupier.getPoints() < player.getPoints()) {
            messagePrinter.printPlayerAndCroupier(player, croupier);
            messagePrinter.printMessage(playerWonMessage(player));
        } else {
            messagePrinter.printPlayerAndCroupier(player, croupier);
            messagePrinter.printMessage(TIE);
        }
    }

    public void croupiersPlay() {
        messagePrinter.printMessage(CROUPIER_TURN);
        while (croupier.getPoints() < 17) {
            croupier.getHand().add(deck.getCard());
        }
    }

    public String playerWonMessage(Player player) {
        return String.format("You won, %s, you lucky guy!", player.getName());
    }
}
