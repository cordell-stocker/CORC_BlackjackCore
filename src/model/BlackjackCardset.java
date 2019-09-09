package model;

import javafxextend.standard.Cardset;
import javafxextend.structure.GUIController;
import standard.Card;
import view.BlackjackVisualHand;

import java.util.Collection;

public class BlackjackCardset extends Cardset {

    private BlackjackVisualHand visualHand;

    @Override
    public boolean addCard(Card card) {
        GUIController.waitOnGUI();
//        visualHand.addCard(card);
        return super.addCard(card);
    }

    @Override
    public boolean removeCard(Card card) {
        GUIController.waitOnGUI();
//        visualHand.removeCard(card);
        return super.removeCard(card);
    }

    @Override
    public boolean addCards(Collection<Card> cards) {
        GUIController.waitOnGUI();
        for (Card card : cards) {
//            visualHand.addCard(card);
        }
        return super.addCards(cards);
    }

    @Override
    public boolean removeCards(Collection<Card> cards) {
        GUIController.waitOnGUI();
        for (Card card: cards) {
//            visualHand.removeCard(card);
        }
        return super.removeCards(cards);
    }

    public void setVisualHand(BlackjackVisualHand visualHand) {
        this.visualHand = visualHand;
    }
}
