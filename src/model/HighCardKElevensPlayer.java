package model;

import standard.Card;
import standard.Cardset;
import structure.ICardsetListener;
import structure.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public class HighCardKElevensPlayer extends AbstractPlayer {

    private final List<IChangeListener<Integer>> SCORE_LISTENERS = new ArrayList<>();

    private int SCORE = 0;
    private final Cardset CARDSET;

    public HighCardKElevensPlayer(String name, Cardset cardset) {
        super(name);
        this.CARDSET = cardset;
    }

    /**
     * For the student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#addCard(Card)} method.
     * SHOULD update this player's score.
     *
     * @param card the Card to be added to this player's hand.
     */
    public void addCard(Card card) {
        this.CARDSET.addCard(card);
    }

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#clearCards()} method.
     * SHOULD set this player's score back to 0.
     */
    public void clearCards() {
        this.CARDSET.clear();
    }

    /**
     * For student to implement.
     * <p>
     * MUST wrap the {@link AbstractBlackjackHand#getCardCount()} method.
     *
     * @return number of Cards in the hand.
     */
    public int getCardCount() {
        return this.CARDSET.size();
    }

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

    private void fireScoreListeners(int oldScore, int newScore) {
        for (IChangeListener<Integer> listener : this.SCORE_LISTENERS) {
            listener.valueChanged(oldScore, newScore);
        }
    }

    public void addScoreListener(IChangeListener<Integer> listener) {
        this.SCORE_LISTENERS.add(listener);
        this.setScore(this.SCORE);
    }

    public void removeScoreListener(IChangeListener<Integer> listener) {
        this.SCORE_LISTENERS.remove(listener);
    }


    public void addCardsetListener(ICardsetListener<Card> listener) {
        this.CARDSET.addCardsetListener(listener);
    }

    public void removeCardsetListener(ICardsetListener<Card> listener) {
        this.CARDSET.removeCardsetListener(listener);
    }
}
