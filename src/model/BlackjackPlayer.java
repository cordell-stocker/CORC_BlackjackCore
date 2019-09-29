package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import standard.Card;
import standard.Cardset;
import standard.Deck;
import structure.ICardsetListener;
import structure.IChangeListener;
import view.BlackjackVisualHand;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class BlackjackPlayer<C extends AbstractBlackjackController> {

    private final List<IChangeListener<Integer>> SCORE_LISTENERS = new ArrayList<>();
    private final List<IChangeListener<Integer>> TOKEN_LISTENERS = new ArrayList<>();

    private int SCORE = 0;
    private int TOKENS = 0;
    private final Cardset CARDSET;
    private final String NAME;

    public BlackjackPlayer(String name, Cardset cardset) {
        this.NAME = name;
        this.CARDSET = cardset;
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
        return this.SCORE;
    }

    /**
     * @param value the new score for this player.
     */
    public void setScore(int value) {
        this.fireScoreListeners(this.SCORE, value);
        this.SCORE = value;
    }

    /**
     * @return this player's tokens.
     */
    public int getTokens() {
        return this.TOKENS;
    }

    /**
     * @param value the new tokens for this player.
     */
    public void setTokens(int value) {
        this.fireTokenListeners(this.TOKENS, value);
        this.TOKENS = value;
    }

    /**
     * @return the name of this player.
     */
    public String getName() {
        return this.NAME;
    }

    private void fireScoreListeners(int oldScore, int newScore) {
        for (IChangeListener<Integer> listener : this.SCORE_LISTENERS) {
            listener.valueChanged(oldScore, newScore);
        }
    }

    private void fireTokenListeners(int oldTokens, int newTokens) {
        for (IChangeListener<Integer> listener : this.TOKEN_LISTENERS) {
            listener.valueChanged(oldTokens, newTokens);
        }
    }

    public void addScoreListener(IChangeListener<Integer> listener) {
        this.SCORE_LISTENERS.add(listener);
        this.setScore(this.SCORE);
    }

    public void removeScoreListener(IChangeListener<Integer> listener) {
        this.SCORE_LISTENERS.remove(listener);
    }

    public void addTokenListener(IChangeListener<Integer> listener) {
        this.TOKEN_LISTENERS.add(listener);
        this.setTokens(this.TOKENS);
    }

    public void removeTokenListener(IChangeListener<Integer> listener) {
        this.TOKEN_LISTENERS.remove(listener);
    }

    public void addCardsetListener(ICardsetListener<Card> listener) {
        this.CARDSET.addCardsetListener(listener);
    }

    public void removeCardsetListener(ICardsetListener<Card> listener) {
        this.CARDSET.removeCardsetListener(listener);
    }

}
