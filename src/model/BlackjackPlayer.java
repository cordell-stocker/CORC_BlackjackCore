package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import observablestandard.Cardset;
import observablestandard.Deck;
import standard.Card;

public abstract class BlackjackPlayer<C extends BlackjackController> {

    private final SimpleIntegerProperty SCORE = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty TOKENS = new SimpleIntegerProperty(0);
    private final Cardset CARDSET = new Cardset();

    public abstract void takeTurn(C controller, Deck deck);

    public abstract void addCard(Card card);

    /**
     * For student to implement.
     *
     * Just needs to wrap the BlackjackHand#clearCards() method.
     */
    public abstract void clearCards();

    /**
     * For student to implement.
     *
     * Just needs to wrap the BlackjackHand#getCardCount() method.
     *
     * @return the number of Cards in this player's hand.
     */
    public abstract int getCardCount();

    /**
     * For student to implement.
     *
     * @return the amount this player is bidding.
     */
    public abstract int bid(C controller);

    /**
     *
     * @return this player's score.
     */
    public int getScore() {
        return this.SCORE.get();
    }

    /**
     *
     * @param value the new score for this player.
     */
    public void setScore(int value) {
        this.SCORE.set(value);
    }

    /**
     *
     * @return this player's tokens.
     */
    public int getTokens() {
        return this.TOKENS.get();
    }

    /**
     *
     * @param value the new tokens for this player.
     */
    public void setTokens(int value) {
        this.TOKENS.set(value);
    }

    /**
     * Students should not touch this method.
     *
     * @param cards Card[] to be bound to the inner observablestandard.Cardset
     */
    public void bindArray(Card[] cards) {
        this.CARDSET.bind(cards);
    }

    public void addScoreListener(ChangeListener<? super Number> listener) {
        this.SCORE.addListener(listener);
    }

    public void removeScoreListener(ChangeListener<? super Number> listener) {
        this.SCORE.removeListener(listener);
    }

    public void addTokenListener(ChangeListener<? super Number> listener) {
        this.TOKENS.addListener(listener);
    }

    public void removeTokenListener(ChangeListener<? super Number> listener) {
        this.TOKENS.removeListener(listener);
    }

    public void addCardsetListener(ListChangeListener<Card> listener) {
        this.CARDSET.addListener(listener);
    }

    public void removeCardsetListener(ListChangeListener<Card> listener) {
        this.CARDSET.removeListener(listener);
    }
}