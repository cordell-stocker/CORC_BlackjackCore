package model;

import standard.Card;
import standard.Cardset;

public class HighCardPlayer extends PlayerWithScore {

    private final Cardset CARDSET;

    public HighCardPlayer(String name, Cardset cardset) {
        super(name, cardset);
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

}
