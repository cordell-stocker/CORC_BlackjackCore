package model;

import highcard.HighCardController;
import javafxextend.standard.Deck;
import standard.Card;

public class HighCardPlayer extends BlackjackPlayer<HighCardController> {

    private final BlackjackCardset CARDSET = new BlackjackCardset();

    public HighCardPlayer(String name) {
        super(name);
        this.bindCardset(this.CARDSET);
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
