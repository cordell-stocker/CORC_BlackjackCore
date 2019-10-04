package kelevensbak;

import model.AbstractKElevensController;
import model.BlackjackDeck;
import model.KElevensPlayer;
import standard.Cardset;
import structure.IDeck;
import view.AbstractGUIController;
import view.BlackjackKElevensGUIController;

import java.util.ArrayList;

public class Runner {
	public static void main(String[] args) {
		AbstractGUIController.setTitle("KElevensBak");
		BlackjackDeck deck = new BlackjackDeck();
		IDeck.setDeck(deck);

		KElevensPlayer human = new KElevensPlayer("You", new Cardset(new ArrayList<>()));
		KElevensPlayer dealer = new KElevensPlayer("Dealer", new Cardset(new ArrayList<>()));
		AbstractKElevensController gameController = new KElevensBakController(human, dealer, deck);
		BlackjackKElevensGUIController.setGameController(gameController);
		BlackjackKElevensGUIController.launchGUI();
	}
}
