package solution;

import model.AbstractBlackjackHand;
import standard.Card;
import standard.Face;
import standard.Suit;

@SuppressWarnings("WeakerAccess")
public class BlackjackHand extends AbstractBlackjackHand {

    private Card[] hand;

    public BlackjackHand() {
        this.hand = new Card[5];
    }

    /**
     * For student to implement.
     * <p>
     * SHOULD add the given card into the next empty slot.
     * SHOULD update this player's score.
     * <p>
     * Another solution is to keep track of the next empty
     * slot as a class variable.
     *
     * @param card Card to be added.
     */
    @Override
    public void addCard(Card card) {
        super.addCard(card); // Given. Do Not Touch!
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
     * MUST set all indexes in this.hand to null.
     */
    @SuppressWarnings("ExplicitArrayFilling")
    @Override
    public void clearCards() {
        super.clearCards(); // Given. Do Not Touch!
        for (int i = 0; i < this.hand.length; i++) {
            this.hand[i] = null;
        }
    }

    /**
     * For student to implement.
     *
     * @return number of non-null Cards in this.hand.
     */
    @Override
    public int getCardCount() {
        int count = 0;
        //noinspection ForLoopReplaceableByForEach
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
     * This is a stateless implementation.
     * Students are allowed to add attributes to
     * this class to be stateful if desired.
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
     * There are many, many ways to implement this method.
     * The students' go-to will most likely be a chain of if-elseif statements.
     * Some may use a switch statement.
     * If they read javadoc for how standard.Face is implemented, they may manage this way.
     *
     * @param card Card to get the value from.
     * @return The value of the card parameter.
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

    /**
     * For student to implement.
     *
     * @return the stored Cards.
     */
    @Override
    public Card[] getCards() {
        return this.hand;
    }
}
