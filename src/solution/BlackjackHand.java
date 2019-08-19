package solution;

import model.BlackjackPlayer;
import standard.*;

public class BlackjackHand {

    private Card[] hand;

    public BlackjackHand(BlackjackPlayer player) {
        this.hand = new Card[5];
        player.bindArray(this.hand);
    }

    /**
     * For student to implement.
     *
     * @param card Card to be added.
     */
    public void addCard(Card card) {
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
     *
     * Needs to set all indexes in this.hand to null.
     */
    public void clearCards() {
        for (int i = 0; i < this.hand.length; i++) {
            this.hand[i] = null;
        }
    }

    /**
     * For student to implement.
     *
     * @return number of non-null Cards in this.hand.
     */
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
     *
     * Should be called whenever a Card is added.
     *
     * This is a stateless implementation.
     * Students are allowed to add attributes to
     * this class to be stateful if desired.
     */
    public int getHandScore() {
        int aceCount = 0;
        int score = 0;
        for (int i = 0; i < this.hand.length; i++) {
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
     *
     * There are many, many ways to implement this method.
     * The students' go-to will most likely be a chain of if-elseif statements.
     * Some may use a switch statement.
     * If they read javadoc for how standard.Face is implemented, they may manage this way.
     *
     * @param card Card to get the value from.
     * @return The value of the card parameter.
     */
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
    public Card[] getCards() {
        return this.hand;
    }
}
