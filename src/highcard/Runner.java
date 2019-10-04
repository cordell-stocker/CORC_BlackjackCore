package highcard;

import model.*;
import standard.Cardset;
import structure.IDeck;
import view.AbstractGUIController;
import view.HighCardGUIController;

import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {
        AbstractGUIController.setTitle("High Card");
        BlackjackDeck deck = new BlackjackDeck();
        IDeck.setDeck(deck);

        HighCardPlayer human = new HighCardPlayer("You", new Cardset(new ArrayList<>()));
        HighCardPlayer dealer = new HighCardPlayer("Dealer", new Cardset(new ArrayList<>()));
        AbstractHighCardController gameController = new HighCardController(human, dealer, deck);
        HighCardGUIController.setGameController(gameController);
        HighCardGUIController.launchGUI();
    }
}
