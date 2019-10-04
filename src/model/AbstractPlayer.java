package model;

import standard.Card;
import standard.Cardset;
import structure.ICardsetListener;

public abstract class AbstractPlayer {

    private final String NAME;
    private final Cardset CARDSET;

    public AbstractPlayer(String name, Cardset cardset) {
        this.NAME = name;
        this.CARDSET = cardset;
    }

    public abstract void addCard(Card card);

    public abstract void clearCards();

    public abstract int getCardCount();

    public String getName() {
        return this.NAME;
    }


    public void addCardsetListener(ICardsetListener<Card> listener) {
        this.CARDSET.addCardsetListener(listener);
    }

    public void removeCardsetListener(ICardsetListener<Card> listener) {
        this.CARDSET.removeCardsetListener(listener);
    }
}
