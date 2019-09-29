package model;

import highcard.HighCardController;
import standard.Card;
import standard.Cardset;
import standard.Deck;

public class HighCardPlayer extends BlackjackPlayer<HighCardController> {

    private final Cardset CARDSET;

    public HighCardPlayer(String name, Cardset cardset) {
        super(name, cardset);
        this.CARDSET = cardset;
    }

    @Override
    public void takeTurn(HighCardController controller, Deck deck) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addCard(Card card) {
        this.CARDSET.addCard(card);
    }

    @Override
    public void clearCards() {
        this.CARDSET.clear();
    }

    @Override
    public int getCardCount() {
        return this.CARDSET.size();
    }

    @Override
    public int bid(HighCardController controller) {
        throw new UnsupportedOperationException();
    }
}
