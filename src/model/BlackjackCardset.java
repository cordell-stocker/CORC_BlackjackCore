package model;

import javafxextend.standard.Cardset;
import javafxextend.structure.GUIController;
import standard.Card;

public class BlackjackCardset extends Cardset {

    @Override
    public boolean addCard(Card card) {
        GUIController.waitOnGUI();
        return super.addCard(card);
    }
}
