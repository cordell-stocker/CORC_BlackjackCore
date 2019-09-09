package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafxextend.standard.Deck;
import standard.Card;
import view.BlackjackVisualHand;

@SuppressWarnings("unused")
public abstract class BlackjackPlayer<C extends AbstractBlackjackController> {

    private final SimpleIntegerProperty SCORE = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty TOKENS = new SimpleIntegerProperty(0);
    private final BlackjackCardset CARDSET = new BlackjackCardset();
    private final String NAME;

    public BlackjackPlayer(String name) {
        this.NAME = name;
    }

    protected void bindBlackjackHand(AbstractBlackjackHand hand) {
        this.CARDSET.bind(hand.getCards());
    }

    /**
     * For student to implement.
     * <p>
     * SHOULD prevent the player from playing (end turn) once
     * their score goes over 21.
     * <p>
     * MUST contain all logic needed for hitting or staying
     *
     * @param controller the game controller.
     * @param deck       the deck used in the game.
     */
    public abstract void takeTurn(C controller, Deck deck);

    /**
     * For the student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#addCard(Card)} method.
     * SHOULD update this player's score.
     *
     * @param card the Card to be added to this player's hand.
     */
    public abstract void addCard(Card card);

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#clearCards()} method.
     * SHOULD set this player's score back to 0.
     */
    public abstract void clearCards();

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#getCardCount()} method.
     *
     * @return number of Cards in the hand.
     */
    public abstract int getCardCount();

    /**
     * For student to implement.
     * <p>
     * MUST remove tokens, and return the amount removed.
     *
     * @return the amount this player is bidding.
     */
    public abstract int bid(C controller);

    /**
     * @return this player's score.
     */
    public int getScore() {
        return this.SCORE.get();
    }

    /**
     * @param value the new score for this player.
     */
    public void setScore(int value) {
        this.SCORE.set(value);
    }

    /**
     * @return this player's tokens.
     */
    public int getTokens() {
        return this.TOKENS.get();
    }

    /**
     * @param value the new tokens for this player.
     */
    public void setTokens(int value) {
        this.TOKENS.set(value);
    }

    /**
     * @return the name of this player.
     */
    public String getName() {
        return this.NAME;
    }

    public void addScoreListener(ChangeListener<? super Number> listener) {
        this.SCORE.addListener(listener);
        this.SCORE.set(this.SCORE.get());
    }

    public void removeScoreListener(ChangeListener<? super Number> listener) {
        this.SCORE.removeListener(listener);
    }

    public void addTokenListener(ChangeListener<? super Number> listener) {
        this.TOKENS.addListener(listener);
        this.TOKENS.set(this.TOKENS.get());
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

    public void setVisualHand(BlackjackVisualHand visualHand) {
        this.CARDSET.setVisualHand(visualHand);
    }
}
