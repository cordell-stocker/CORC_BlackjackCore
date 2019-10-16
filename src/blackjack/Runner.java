package blackjack;

import corc.standard.Cardset;
import corc.structure.IDeck;
import blackjackcore.model.AbstractBlackjackController;
import blackjackcore.model.BlackjackDeck;
import blackjackcore.model.player.BlackjackPlayer;
import blackjackcore.view.BlackjackKElevensGUIController;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        BlackjackDeck deck = new BlackjackDeck();
        IDeck.setDeck(deck);

        BlackjackPlayer human = new HumanBlackjackPlayer("You", new Cardset(new ArrayList<>()));
        BlackjackPlayer dealer = new ComputerBlackjackPlayer();
        AbstractBlackjackController gameController = new BlackjackController(human, dealer, deck);
        BlackjackKElevensGUIController.setGameController(gameController);
        BlackjackKElevensGUIController.launchGUI();
    }
}

