package solution;

import model.AbstractBlackjackController;
import model.BlackjackDeck;
import model.BlackjackPlayer;
import structure.Deck;

public class Runner {
    public static void main(String[] args) {
        BlackjackDeck deck = new BlackjackDeck();
        Deck.setDeck(deck);

        BlackjackPlayer human = new BlackjackHumanPlayer();
        BlackjackPlayer dealer = new BlackjackComputerPlayer();
        AbstractBlackjackController gameController = new BlackjackController(human, dealer, deck);
        gameController.launchWithGUI();
    }
}

