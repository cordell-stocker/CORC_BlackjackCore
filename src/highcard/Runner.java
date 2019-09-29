package highcard;

import model.AbstractBlackjackController;
import model.BlackjackDeck;
import model.BlackjackPlayer;
import model.HighCardPlayer;
import standard.Cardset;
import structure.IDeck;

import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {
        BlackjackDeck deck = new BlackjackDeck();
        IDeck.setDeck(deck);

        BlackjackPlayer human = new HighCardPlayer("You", new Cardset(new ArrayList<>()));
        BlackjackPlayer dealer = new HighCardPlayer("Dealer", new Cardset(new ArrayList<>()));
        AbstractBlackjackController gameController = new HighCardController(human, dealer, deck);
        gameController.launchWithGUI();
    }
}
