package model;

import javafx.GUIController;
import observablestandard.Cardset;
import standard.Card;

public class BlackjackCardset extends Cardset {

    @Override
    public boolean addCard(Card card) {
        GUIController.waitOnGUI();
        return super.addCard(card);
    }
}