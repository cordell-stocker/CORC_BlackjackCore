package highcard;

import model.AbstractBlackjackController;
import model.BlackjackDeck;
import model.BlackjackPlayer;
import model.HighCardPlayer;
import structure.IDeck;

public class Runner {

    public static void main(String[] args) {
        BlackjackDeck deck = new BlackjackDeck();
        IDeck.setDeck(deck);

        BlackjackPlayer human = new HighCardPlayer("You");
        BlackjackPlayer dealer = new HighCardPlayer("Dealer");
        AbstractBlackjackController gameController = new HighCardController(human, dealer, deck);
        gameController.launchWithGUI();
    }
}
