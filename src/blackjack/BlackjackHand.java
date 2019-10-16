package blackjack;

import corc.standard.*;
import blackjackcore.model.AbstractBlackjackHand;

/**
 * Represents a player's hand.
 * <p>
 * For this project, there is only ever the need to:
 * 1) add a Card to the hand.
 * 2) clear (remove) all Cards from the hand.
 * 3) calculate the score of the hand.
 * 4) get the number of Cards in the hand.
 * 5) any functionality that aids in the above items.
 */
public class BlackjackHand extends AbstractBlackjackHand {

    private Card[] hand;

    public BlackjackHand() {
        this.hand = new Card[5];
    }

    /**
     * For student to implement.
     * <p>
     * SHOULD add the given card into the next empty index.
     *
     * @param card Card to be added.
     */
    @Override
    public void addCard(Card card) {
        /* This can also be done using a
         * class level variable to track
         * the next open slot.
         */
        int index = 0;
        boolean searching = true;
        while (searching) {
            if (this.hand[index] == null) {
                this.hand[index] = card;
                searching = false;
            }
            index++;
        }
    }

    /**
     * For student to implement.
     * <p>
     * MUST set all indexes in the hand to null.
     */
    @Override
    public void clearCards() {
        for (int i = 0; i < this.hand.length; i++) {
            this.hand[i] = null;
        }
    }

    /**
     * For student to implement.
     * <p>
     * MUST return the number of Cards in the hand.
     *
     * @return number of Cards in the hand.
     */
    @Override
    public int getCardCount() {
        int count = 0;
        for (int i = 0; i < this.hand.length; i++) {
            if (this.hand[i] != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * For student to implement.
     * <p>
     * Calculates the score of the hand, and
     * returns that value.
     *
     * @return the score of this hand.
     */
    @Override
    public int getHandScore() {
        int aceCount = 0;
        int score = 0;
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < this.hand.length; i++) {
            if (this.hand[i] == null) {
                continue;
            }
            String face = this.hand[i].getFace().getName();
            if (face.equals("ACE")) {
                aceCount++;
            }
            score += this.getCardValue(this.hand[i]);
        }

        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    /**
     * For student to implement.
     * <p>
     * Calculates the value of a single Card, and returns
     * that value.
     *
     * @param card the Card to get the value of.
     * @return The value of the Card.
     */
    @Override
    public int getCardValue(Card card) {
        Face face = card.getFace();
        String faceName = face.getName();
        if (faceName.equals("ACE")) {
            return 11;
        } else if (faceName.equals("JACK") || faceName.equals("QUEEN") || faceName.equals("KING")) {
            return 10;
        } else {
            return face.getValue();
        }
    }

    @Override
    public Card[] getCards() {
        return this.hand;
    }
}
