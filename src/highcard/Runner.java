package highcard;

import model.*;
import standard.Cardset;
import structure.IDeck;
import view.HighCardKElevensGUIController;

import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {
        BlackjackDeck deck = new BlackjackDeck();
        IDeck.setDeck(deck);

        HighCardKElevensPlayer human = new HighCardKElevensPlayer("You", new Cardset(new ArrayList<>()));
        HighCardKElevensPlayer dealer = new HighCardKElevensPlayer("Dealer", new Cardset(new ArrayList<>()));
        AbstractHighCardController gameController = new HighCardController(human, dealer, deck);
        HighCardKElevensGUIController.setGameController(gameController);
        HighCardKElevensGUIController.launchGUI();
    }
}
