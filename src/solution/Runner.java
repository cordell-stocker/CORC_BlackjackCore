package solution;

import model.AbstractBlackjackController;
import model.BlackjackDeck;
import model.BlackjackPlayer;
import structure.IDeck;
import view.BlackjackGUIController;

public class Runner {
    public static void main(String[] args) {
        BlackjackDeck deck = new BlackjackDeck();
        IDeck.setDeck(deck);

        BlackjackPlayer human = new HumanBlackjackPlayer();
        BlackjackPlayer dealer = new ComputerBlackjackPlayer();
        AbstractBlackjackController gameController = new BlackjackController(human, dealer, deck);
        BlackjackGUIController.setGameController(gameController);
        BlackjackGUIController.launchGUI();
    }
}

